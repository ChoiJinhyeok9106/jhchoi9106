/** 
 * 
 */
package kr.co.codefarm.svcm.systemmanager.alert.controller;

import kr.co.codefarm.svcm.commons.annotation.AlarmRelay;
import kr.co.codefarm.svcm.commons.annotation.AlarmType;
import kr.co.codefarm.svcm.commons.annotation.ManagedAPI;
import kr.co.codefarm.svcm.commons.api.dto.AlarmDto;
import kr.co.codefarm.svcm.commons.controller.BaseController;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.utils.EtcUtil;
import kr.co.codefarm.svcm.commons.utils.ObjectUtil;
import kr.co.codefarm.svcm.systemmanager.alert.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author KKC
 *
 */
@ManagedAPI
@RestController
@RequestMapping(value = "/system-manager/alert")
public class AlertController extends BaseController {

	@Autowired
	private AlertService alertService;
	
	//Alarm Controller
	@GetMapping({""})
	public List<Map<String, Object>> getAlertList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = alertService.getAlertList(parameterMap.getMap());
		return list;
	}
	
	@PostMapping("")
	public Object insertAlert(ParameterMap parameterMap) {
		Map<String, Object> insObj = alertService.insertAlert(parameterMap.getMap());
		return insObj;
	}
	
	
	@PutMapping("")
	public Object updateAlert(String alertSeq, ParameterMap parameterMap) {
		int insCnt = alertService.updateAlert(parameterMap.getMap());
		return insCnt;
	}
	
	
	@DeleteMapping("")
	public Object deleteAlert(String alarmSeq, ParameterMap parameterMap) {
		String pAlarmSeq = (String) parameterMap.get("pAlarmSeq");
		
		int delCnt = alertService.deleteAlert(pAlarmSeq);
		return delCnt;
	}
	
	
	//Alarm Daesang Controller
	@GetMapping({"/daesang"})
	public List<Map<String, Object>> getDaesangList(ParameterMap parameterMap) {
		String pAlarmSeq = (String) parameterMap.get("pAlarmSeq");
		String pAlarmDaesangGb = (String) parameterMap.get("pAlarmDaesangGb");
			
		List<Map<String, Object>> list = alertService.getDaesangList(pAlarmSeq, pAlarmDaesangGb);
		return list;
	}
	
	//Alarm Daesang Controller
	@GetMapping({"/daesang-user"})
	public List<Map<String, Object>> getDaesangUserList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = alertService.getDaesangUserList(parameterMap.getMap());
		return list;
	}
		
	@PostMapping("/daesang")
	public Object insertDaesang(String alarmSeq, ParameterMap parameterMap) {
		int insCnt = alertService.insertDaesang(parameterMap.getMap());
		return insCnt;
	}
		
	@PutMapping("/daesang")
	public Object updateDaesang(String alarmSeq, ParameterMap parameterMap) {
		int insCnt = alertService.updateDaesang(parameterMap.getMap());
		return insCnt;
	}
		
		
	@DeleteMapping("/daesang")
	public Object deleteDaesang(String alarmSeq, ParameterMap parameterMap) {
		String pAlarmSeq = (String) parameterMap.get("pAlarmSeq");
		String pAlarmDaesangGb = (String) parameterMap.get("pAlarmDaesangGb");
		String pDaesangjaId = (String) parameterMap.get("pDaesangjaId");
		
		int delCnt = alertService.deleteDaesang(pAlarmSeq, pAlarmDaesangGb, pDaesangjaId);
		return delCnt;
	}
	
	//Alarm Controller
	@GetMapping("/user/alarms")
	public List<Map<String, Object>> getUserAlarmList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pAlarmGb = (String) parameterMap.get("pAlarmGb");
		String pUserId = (String) userDetailsImpl.getUserId();
		String pDefaultAuthCd = (String) userDetailsImpl.getDefaultAuthCd();
		
		//pPageCount = "5";
		
		List<Map<String, Object>> list = alertService.getUserAlarmList(pAlarmGb, pUserId, pDefaultAuthCd);
		return list;
	}
	
	@PostMapping("/user/alarm")
	public Object insertUserAlarm(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		int insertCnt = alertService.insertUserAlarm(parameterMap.getMap(), pUserId);
		return insertCnt;
	}
	
	@PutMapping("/user/alarm")
	public Object updateUserAlarm(String alertSeq, ParameterMap parameterMap) {
		int insCnt = alertService.updateUserAlarm(parameterMap.getMap());
		return insCnt;
	}
	
	@DeleteMapping("/user/alarm/{alarmSeq}")
	public Object deleteUserAlarm(@PathVariable(value="alarmSeq") String alarmSeq, ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		
		int delCnt = alertService.deleteUserAlarm(alarmSeq, pUserId);
		return delCnt;
	}
	
	//Alarm Controller
	@GetMapping("/user/alarm/datas")
	public List<Map<String, Object>> getUserAlarmDataList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		String pAlarmMinTime = (String) parameterMap.get("pAlarmMinTime");
		
		List<Map<String, Object>> list = alertService.getUserAlarmDataList(pUserId, pAlarmMinTime);
		return list;
	}
	
	//Alarm Controller
	@PutMapping("/user/alarm/data")
	public Object updateUserAlarmData(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		int insCnt = 0;
		
		if(parameterMap.getMap().get("USER_ID").equals(pUserId)) {
			insCnt = alertService.updateUserAlarmData(parameterMap.getMap());
		}
		
		return insCnt;
	}
	
	@AlarmRelay
	public List<AlarmDto> getAllAlarmList() {
		List<AlarmDto> retList = new ArrayList<>();
		List<Map<String, Object>> list = alertService.getAllAlarmList();
		
		for (Map<String, Object> map : list) {
			AlarmDto alarmDto = (AlarmDto) ObjectUtil.convertMapToObject(map, new AlarmDto());
			retList.add(alarmDto);
		}
		
		return retList;
	}
	
	@AlarmRelay(type = AlarmType.ALARM)
	public void alarm(AlarmDto alarmDto, List<Map<String, Object>> dataParameter, HttpServletRequest request) {
		List<Map<String, Object>> alarmDataList = new ArrayList<Map<String, Object>>();
		
		//알림 등록정보
		String alarmSeq = (String) alarmDto.getALARM_SEQ();
		String alarmGb = (String) alarmDto.getALARM_GB();
		String alarmDetailGb = (String) alarmDto.getALARM_DETAIL_GB();
		String alarmDaesangGb = (String) alarmDto.getALARM_DAESANG_GB();
		String daesangjaColumnId = (String) alarmDto.getDAESANGJA_COLUMN_ID();
		String alarmChkGb = (String) alarmDto.getALARM_CHK_GB();
		String title = (String) alarmDto.getTITLE();
		String text = (String) alarmDto.getTEXT();
		
		//API 정보
		String apiSeq = (String) alarmDto.getAPI_SEQ();
		String serviceCd = (String) alarmDto.getSERVICE_CD();
		String apiNm = (String) alarmDto.getAPI_NM();
		String apiUrl = (String) alarmDto.getAPI_URL();
		String methodGb = (String) alarmDto.getMETHOD_GB();
		String restApiDesc = (String) alarmDto.getREST_API_DESC();
		String remark = (String) alarmDto.getREMARK();
		/*		
		System.out.println("ALARM_CHK_GB     		############################################## AlarmDto : " + alarmChkGb);
		System.out.println("TITLE            		############################################## AlarmDto : " + title);
		System.out.println("TEXT             		############################################## AlarmDto : " + text);
		System.out.println("SERVICE_CD       		############################################## AlarmDto : " + serviceCd);
		System.out.println("REST_API_DESC    		############################################## AlarmDto : " + restApiDesc);
		System.out.println("REMARK           		############################################## AlarmDto : " + remark);
		System.out.println("METHOD_GB        		############################################## AlarmDto : " + methodGb);
		System.out.println("API_URL          		############################################## AlarmDto : " + apiUrl);
		System.out.println("API_SEQ          		############################################## AlarmDto : " + apiSeq);
		System.out.println("API_NM           		############################################## AlarmDto : " + apiNm);
		System.out.println("ALARM_SEQ        		############################################## AlarmDto : " + alarmSeq);
		System.out.println("ALARM_DAESANG_GB 		############################################## AlarmDto : " + alarmDaesangGb);
		System.out.println("DAESANGJA_COLUMN_ID 	############################################## AlarmDto : " + daesangjaColumnId);
		System.out.println("ALARM_GB         		############################################## AlarmDto : " + alarmGb);
		*/
		
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ text before :"+text);
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ dataParameter :"+dataParameter);
		for (Map<String, Object> mapItem : dataParameter) {
			for (String key : mapItem.keySet()) {
				if(text.indexOf("${"+key+"}") > -1) {
					text = text.replaceAll("\\$\\{"+key+"\\}", (String) mapItem.get(key));
				}
				
				if(title.indexOf("${"+key+"}") > -1) {
					title = title.replaceAll("\\$\\{"+key+"\\}", (String) mapItem.get(key));
				}
			}
		}
		for (Map<String, Object> mapItem : dataParameter) {
			for (String key : mapItem.keySet()) {
//				int t = text.indexOf("${"+key+"}");
//				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ key :"+key + "  text.indexOf(\"${\"+key+\"}\" : " + t);
				if(text.indexOf(key) > -1) {
					text = text.replaceAll(key, (String) mapItem.get(key));
				}
				
				if(title.indexOf(key) > -1) {
					title = title.replaceAll(key, (String) mapItem.get(key));
				}
			}
		}
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ text after :"+text);
		
		//01 : 권한 지정, 02 : 사용자 지정 
		if(alarmDaesangGb.equals("01") || alarmDaesangGb.equals("02")) {
			/**************************************************************************************************************************
			 * 알림대상 리스트 가져오기
			 **************************************************************************************************************************/			
			List<Map<String, Object>> alarmDaesangList = alertService.getDaesangList(alarmSeq, alarmDaesangGb);
			
			for(int i=0 ; i < alarmDaesangList.size() ; i++) {
				Map<String, Object> daesangjaMap = alarmDaesangList.get(i);
				String daesangjaId = (String) daesangjaMap.get("DAESANGJA_ID");
				
				/**************************************************************************************************************************
				 * 권한 베이스 알림
				 **************************************************************************************************************************/
				if(alarmDaesangGb.equals("01")) {
					//해당 권한에 속하는 유저목록을 가져온다.
					List<Map<String, Object>> userList = alertService.getAuthUserList(daesangjaId); 
					
					for(int j=0 ; j < userList.size() ; j++) {
						Map<String, Object> alarmDataObject = new HashMap<>();
						Map<String, Object> userMap = userList.get(j);
						String userId = (String) userMap.get("USER_ID");
						
						alarmDataObject.put("USER_ID", userId);
						alarmDataObject.put("ALARM_SEQ", alarmSeq);
						//alarmDataObject.put("ALARM_LIST_SEQ", "자동생성");
						alarmDataObject.put("TITLE", title);
						alarmDataObject.put("TEXT", text);
						alarmDataObject.put("SYSTEM_IP", EtcUtil.getIP(request));
						//alarmDataObject.put("LINK", "기능미구현");
						alarmDataList.add(alarmDataObject);
					}
				}
				/**************************************************************************************************************************
				 * 사용자 베이스 알림
				 **************************************************************************************************************************/
				else if(alarmDaesangGb.equals("02")) {
					Map<String, Object> alarmDataObject = new HashMap<>();
					
					alarmDataObject.put("USER_ID", daesangjaId);
					alarmDataObject.put("ALARM_SEQ", alarmSeq);
					//alarmDataObject.put("ALARM_LIST_SEQ", "자동생성");
					alarmDataObject.put("TITLE", title);
					alarmDataObject.put("TEXT", text);
					alarmDataObject.put("SYSTEM_IP", EtcUtil.getIP(request));
					//alarmDataObject.put("LINK", "기능미구현");
					alarmDataList.add(alarmDataObject);
				}
			}
		}
		/**************************************************************************************************************************
		 * 임의 사용자 베이스 알림
		 **************************************************************************************************************************/
		else if(alarmDaesangGb.equals("03")) {
			System.out.println("####################################### dataParameter.toString() : "+dataParameter.toString());
			for (Map<String, Object> mapItem : dataParameter) {
				if(mapItem.size() > 0) {
					Map<String, Object> alarmDataObject = new HashMap<>();
					if(daesangjaColumnId.indexOf(";") > -1 && mapItem.containsKey(daesangjaColumnId.split(";")[0])) {
						List userList = (List) mapItem.get(daesangjaColumnId.split(";")[0]);
						
						for(int i=0 ; i < userList.size() ; i++) {
							Map userMap = (Map<String, Object>) userList.get(i);
							String userId = (String) userMap.get(daesangjaColumnId.split(";")[1]);
							
							if(userId != null && !userId.equals("")) {
								/*for (String key : mapItem.keySet()) {
									System.out.println(key + " ############################################## DataParameter : " + mapItem.get(key));
								}*/
								alarmDataObject.put("USER_ID", userId);
								alarmDataObject.put("ALARM_SEQ", alarmSeq);
								//alarmDataObject.put("ALARM_LIST_SEQ", "자동생성");
								alarmDataObject.put("TITLE", title);
								alarmDataObject.put("TEXT", text);
								alarmDataObject.put("SYSTEM_IP", EtcUtil.getIP(request));
								//alarmDataObject.put("LINK", "기능미구현");
								alarmDataList.add(alarmDataObject);
							}
						}
						
					}else {
						String userId = (String) mapItem.get(daesangjaColumnId);
						
						if(userId != null && !userId.equals("")) {
							/*for (String key : mapItem.keySet()) {
								System.out.println(key + " ############################################## DataParameter : " + mapItem.get(key));
							}*/
							alarmDataObject.put("USER_ID", userId);
							alarmDataObject.put("ALARM_SEQ", alarmSeq);
							//alarmDataObject.put("ALARM_LIST_SEQ", "자동생성");
							alarmDataObject.put("TITLE", title);
							alarmDataObject.put("TEXT", text);
							alarmDataObject.put("SYSTEM_IP", EtcUtil.getIP(request));
							//alarmDataObject.put("LINK", "기능미구현");
							alarmDataList.add(alarmDataObject);
						}
					}
				}
			}
		}
		
		String curDate = alertService.insertUserAlarmData(alarmDataList, alarmSeq);
	}
	
	@AlarmRelay(type = AlarmType.LOG)
	public void log(AlarmDto alarmDto, List<Map<String, Object>> dataParameter, HttpServletRequest request) {
		//알림 등록정보
		String alarmSeq = (String) alarmDto.getALARM_SEQ();
		String alarmGb = (String) alarmDto.getALARM_GB();
		String alarmDetailGb = (String) alarmDto.getALARM_DETAIL_GB();
		String alarmDaesangGb = (String) alarmDto.getALARM_DAESANG_GB();
		String daesangjaColumnId = (String) alarmDto.getDAESANGJA_COLUMN_ID();
		String alarmChkGb = (String) alarmDto.getALARM_CHK_GB();
		String title = (String) alarmDto.getTITLE();
		String text = (String) alarmDto.getTEXT();
		String systemId = "";
		String systemIp = "";
		String systemPgm = "";
		
		//API 정보
		String apiSeq = (String) alarmDto.getAPI_SEQ();
		String serviceCd = (String) alarmDto.getSERVICE_CD();
		String apiNm = (String) alarmDto.getAPI_NM();
		String apiUrl = (String) alarmDto.getAPI_URL();
		String methodGb = (String) alarmDto.getMETHOD_GB();
		String restApiDesc = (String) alarmDto.getREST_API_DESC();
		String remark = (String) alarmDto.getREMARK();
		
		//System.out.println("ALARM_CHK_GB     		############################################## AlarmDto : " + alarmChkGb);
		//System.out.println("TITLE            		############################################## AlarmDto : " + title);
		//System.out.println("TEXT             		############################################## AlarmDto : " + text);
		//System.out.println("SERVICE_CD       		############################################## AlarmDto : " + serviceCd);
		//System.out.println("REST_API_DESC    		############################################## AlarmDto : " + restApiDesc);
		//System.out.println("REMARK           		############################################## AlarmDto : " + remark);
		//System.out.println("METHOD_GB        		############################################## AlarmDto : " + methodGb);
		//System.out.println("API_URL          		############################################## AlarmDto : " + apiUrl);
		//System.out.println("API_SEQ          		############################################## AlarmDto : " + apiSeq);
		//System.out.println("API_NM           		############################################## AlarmDto : " + apiNm);
		//System.out.println("ALARM_SEQ        		############################################## AlarmDto : " + alarmSeq);
		//System.out.println("ALARM_DAESANG_GB 		############################################## AlarmDto : " + alarmDaesangGb);
		//System.out.println("DAESANGJA_COLUMN_ID		############################################## AlarmDto : " + daesangjaColumnId);
		//System.out.println("ALARM_GB         		############################################## AlarmDto : " + alarmGb);
		//System.out.println("ALARM_DETAIL_GB   		############################################## AlarmDto : " + alarmDetailGb);
		
		for (Map<String, Object> mapItem : dataParameter) {
			for (String key : mapItem.keySet()) {
				//System.out.println("KEY   		@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ : " + key);
				//System.out.println("VALUE   	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ : " + (String) mapItem.get(key));
				if(key == "SYSTEM_ID") systemId = (String) mapItem.get(key);
				if(key == "SYSTEM_IP") systemIp = (String) mapItem.get(key);
				if(key == "SYSTEM_PGM") systemPgm = (String) mapItem.get(key);
				
				if(title.indexOf("${"+key+"}") > -1) {
					title = title.replaceAll("\\$\\{"+key+"\\}", (String) mapItem.get(key));
				}
			}
		}
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", systemId);
		paramMap.put("SYSTEM_IP", systemIp);
		paramMap.put("ALARM_SEQ", alarmSeq);
		paramMap.put("TITLE", title);
		paramMap.put("TEXT", dataParameter.toString());
		
		int insCnt = alertService.insertLog(paramMap);
	}

	@GetMapping("/user/marketAlarm")
	public List<Map<String, Object>> marketAlarm(ParameterMap parameterMap,@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {

		String pUserId = (String) userDetailsImpl.getUserId();

		parameterMap.put("USER_ID", pUserId);

		List<Map<String, Object>> list = alertService.marketAlarm(parameterMap.getMap());
		return list;

	}

	@PutMapping("/user/marketAlramSave")
	public Object marketAlarmUpdate(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		int insCnt = 0;

		parameterMap.put("USER_ID", pUserId);

		insCnt = alertService.marketAlarmUpdate(parameterMap.getMap());

		return insCnt;
	}
}
