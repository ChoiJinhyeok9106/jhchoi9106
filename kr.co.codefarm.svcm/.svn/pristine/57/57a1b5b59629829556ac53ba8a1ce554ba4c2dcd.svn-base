/**
 * 
 */
package kr.co.codefarm.svcm.commons.controller.sms;

import kr.co.codefarm.svcm.commons.component.SmsComponent;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.DefaultInfoMap;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/commons/sms")
public class SmsController {

	@Autowired
	private SmsComponent smsComponent;

	@PostMapping({"/send"})
	public int sendSms(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		Map<String, Object> paramMap = new HashMap<>();
		List<String> recvNumbers = new ArrayList<String>();
		List<Map<String, Object>> recvList = new ArrayList<Map<String, Object>>();
		String sendMsg = "";
		String sendNumber = "";
		int returnCnt = 0;
		
		paramMap = (Map<String, Object>) parameterMap.get("data");
		sendMsg = (String) paramMap.get("SEND_MSG");
		sendNumber = (String) paramMap.get("SEND_NUMBER");
		recvList = (List<Map<String, Object>>) paramMap.get("RECV");
		
		// SMS를 보내는 사람은 세션 정보를 우선시 함
		if(sendNumber == null || sendNumber == "") {
			sendNumber = userDetailsImpl.getHpNo();
		}
		
		if(recvList != null && recvList.size() > 0) {
			for(int i=0 ; i < recvList.size() ; i++) {
				Map<String, Object> recvObj = recvList.get(i);
				recvNumbers.add((String) recvObj.get("RECV_NUMBER"));
			}
			returnCnt = smsComponent.sendSms(recvNumbers.toArray(new String[recvNumbers.size()]), sendMsg, sendNumber);

		}
		
		return returnCnt;
	}
	
	@PostMapping({"/send-list"})
	public int sendSmsList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		Map<String, Object> paramMap = new HashMap<>();
		List<Map<String, Object>> recvList = new ArrayList<Map<String, Object>>();
		String recvNumber = "";
		String sendMsg = "";
		String url = "";
		String sendNumber = "";
		int returnCnt = 0;
		
		recvList = (List<Map<String, Object>>) parameterMap.get("JSON_DATA_LIST");
		
		// SMS를 보내는 사람은 세션 정보를 우선시 함
		if(sendNumber == null || sendNumber == "") {
			sendNumber = userDetailsImpl.getHpNo();
		}
		
		if(recvList != null && recvList.size() > 0) {
			for(int i=0 ; i < recvList.size() ; i++) {
				Map<String, Object> recvObj = recvList.get(i);
				recvNumber = (String) recvObj.get("HP_NO");
				url = (String) recvObj.get("LINK_URL");
				String pSeolmunjiNm = (String) recvObj.get("SEOLMUNJI_NM");
				
				String companyNm    = DefaultInfoMap.get("CO_NM") == null ? "":DefaultInfoMap.get("CO_NM").toString();
				
				sendMsg = "["+companyNm+"-"+pSeolmunjiNm+"] " + "포털-설문관리-나의설문지에서 확인바랍니다.";
				//sendMsg +=  url;
				
				
				
				returnCnt += smsComponent.sendSms(recvNumber, sendMsg, sendNumber);
			}
		}
		
		return returnCnt;
	}
}
