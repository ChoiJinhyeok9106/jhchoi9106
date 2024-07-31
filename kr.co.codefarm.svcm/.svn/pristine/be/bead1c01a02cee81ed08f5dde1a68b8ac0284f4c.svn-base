/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.auth.controller;

import kr.co.codefarm.svcm.commons.annotation.AuthRelay;
import kr.co.codefarm.svcm.commons.annotation.ManagedAPI;
import kr.co.codefarm.svcm.commons.api.dto.AuthDto;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.utils.ObjectUtil;
import kr.co.codefarm.svcm.systemmanager.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author KKC
 *
 */
@ManagedAPI
@RestController
@RequestMapping(value = "/system-manager/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@AuthRelay
	public List<AuthDto> addSelectServiceListHandler() {
		List<AuthDto> retList         = new ArrayList<>();
		List<Map<String, Object>> list = authService.getAllAuthList();
		
		for (Map<String, Object> map : list) {
			AuthDto alertDto = (AuthDto) ObjectUtil.convertMapToObject(map, new AuthDto());
			retList.add(alertDto);
		}
		
		return retList;
	}

	//Auth Controller
	@GetMapping({""})
	public List<Map<String, Object>> getAuthList(ParameterMap parameterMap) {
		String pUseYn = parameterMap.get("pUseYn").toString();
		String pAuthGb = parameterMap.get("pAuthGb").toString();
		String pSearchType = "";
		String pSearchArgv = "";
		
		if(parameterMap.containsKey("pSearchType")) {
			pSearchType = parameterMap.get("pSearchType").toString();
		}
		
		if(parameterMap.containsKey("pSearchArgv")) {
			pSearchArgv = parameterMap.get("pSearchArgv").toString();
		}
		String pPageNo = "";
		String pRowCount = "";
		
		if(parameterMap.containsKey("pPageNo")) {
			pPageNo = parameterMap.get("pPageNo").toString();
		}
		
		if(parameterMap.containsKey("pRowCount")) {
			pRowCount = parameterMap.get("pRowCount").toString();
		}
		//pPageCount = "5";
		
		List<Map<String, Object>> list = authService.getAuthList(pUseYn, pAuthGb, pSearchType, pSearchArgv, pPageNo, pRowCount);
		return list;
	}
	
	//wjjoo 2022.03.15 추가
	@GetMapping("/subAuth")
	public List<Map<String, Object>> getSubAuthList(@AuthenticationPrincipal ScvmUserDetailsImpl scvmUserDetailsImpl, ParameterMap parameterMap) {
		parameterMap.put("USER_ID", scvmUserDetailsImpl.getUserId());
		List<Map<String, Object>> list = authService.getSubAuthList(parameterMap.getMap());
		return list;
	}
	
	
	@PostMapping("")
	public Object insertAuth(String authCd, ParameterMap parameterMap) {
		int insCnt = authService.insertAuth(parameterMap.getMap());
		return insCnt;
	}
	
	@GetMapping("/seq")
	public Object getAuthSeq(ParameterMap parameterMap) {
		Object returnSeq = authService.getAuthSeq(parameterMap.getMap());
		return returnSeq;
	}
	
	@GetMapping("/sort-seq")
	public Object getSortSeq(ParameterMap parameterMap) {
		Object returnSeq = authService.getSortSeq(parameterMap.getMap());
		return returnSeq;
	}
	
	@PutMapping("")
	public Object updateAuth(String authCd, ParameterMap parameterMap) {
		int insCnt = authService.updateAuth(parameterMap.getMap());
		return insCnt;
	}
	
	
	@DeleteMapping("")
	public Object deleteAuth(String authCd, ParameterMap parameterMap) {
		String pAuthCd = (String) parameterMap.get("pAuthCd");
		
		int delCnt = authService.deleteAuth(pAuthCd);
		return delCnt;
	}
	
	
	
	//App Auth Controller
	@GetMapping({"/app"})
	public List<Map<String, Object>> getAppAuthList(ParameterMap parameterMap) {
		
		String pAuthCd = (String) parameterMap.get("pAuthCd");
		String pAppId = (String) parameterMap.get("pAppId");
		String pSearchType = (String) parameterMap.get("pSearchType");
		String pSearchArgv = (String) parameterMap.get("pSearchArgv");
		String pPageNo = (String) parameterMap.get("pPageNo");
		String pRowCount = (String) parameterMap.get("pRowCount");
		//pPageCount = "5";
		
		List<Map<String, Object>> list = authService.getAppAuthList(pAuthCd, pAppId, pSearchType, pSearchArgv, pPageNo, pRowCount);
		return list;
	}

	@GetMapping({"/appAllAuth"})
	public List<Map<String, Object>> getAppAllAuthList(ParameterMap parameterMap) {
		String pAppId = (String) parameterMap.get("pAppId");
		List<Map<String, Object>> list = authService.getAppAllAuthList(pAppId);
		return list;
	}
	
	@PostMapping("/app")
	public Object insertAppAuth(String commonCd, ParameterMap parameterMap) {
		int insCnt = authService.insertAppAuth(parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("/app")
	public Object updateAppAuth(String commonCd, ParameterMap parameterMap) {
		int insCnt = authService.updateAppAuth(parameterMap.getMap());
		return insCnt;
	}
	
	
	@DeleteMapping("/app")
	public Object deleteAppAuth(String authCd, ParameterMap parameterMap) {
		String pAppId = (String) parameterMap.get("pAppId");
		String pAuthCd = (String) parameterMap.get("pAuthCd");
		
		int delCnt = authService.deleteAppAuth(pAppId, pAuthCd);
		return delCnt;
	}
	
	
	
	//App Auth Controller
	@GetMapping({"/user-add"})
	public List<Map<String, Object>> getUserAddAuthList(ParameterMap parameterMap) {
		
		String pAuthCd = (String) parameterMap.get("pAuthCd");
		String pUserId = (String) parameterMap.get("pUserId");
		String pSearchType = (String) parameterMap.get("pSearchType");
		String pSearchArgv = (String) parameterMap.get("pSearchArgv");
		String pPageNo = (String) parameterMap.get("pPageNo");
		String pRowCount = (String) parameterMap.get("pRowCount");
		//pPageCount = "5";
		
		List<Map<String, Object>> list = authService.getUserAddAuthList(pAuthCd, pUserId, pSearchType, pSearchArgv, pPageNo, pRowCount);
		return list;
	}
	
	@PostMapping("/user-add")
	public Object insertUserAddAuth(String commonCd, ParameterMap parameterMap) {
		int insCnt = authService.insertUserAddAuth(parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("/user-add")
	public Object updateUserAddAuth(String commonCd, ParameterMap parameterMap) {
		int insCnt = authService.updateUserAddAuth(parameterMap.getMap());
		return insCnt;
	}
	
	
	@DeleteMapping("/user-add")
	public Object deleteUserAddAuth(String authCd, ParameterMap parameterMap) {
		String pUserId = (String) parameterMap.get("pUserId");
		String pAuthCd = (String) parameterMap.get("pAuthCd");
		
		int delCnt = authService.deleteUserAddAuth(pUserId, pAuthCd);
		return delCnt;
	}
	
	// wjjoo 2022.03.15 추가
	@PostMapping("/subuser-add")
	public Object insertSubUserAddAuth(@AuthenticationPrincipal ScvmUserDetailsImpl scvmUserDetailsImpl, String commonCd, ParameterMap parameterMap) {
		int authCheck = authService.getRoleCheck(scvmUserDetailsImpl.getUserId(),(String) parameterMap.get("AUTH_CD"));
		int insCnt = 0;
		if(authCheck > 0) insCnt =authService.insertUserAddAuth(parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("/subuser-add")
	public Object updateSubUserAddAuth(@AuthenticationPrincipal ScvmUserDetailsImpl scvmUserDetailsImpl, String commonCd, ParameterMap parameterMap) {
		int authCheck = authService.getRoleCheck(scvmUserDetailsImpl.getUserId(),(String) parameterMap.get("AUTH_CD"));
		int insCnt = 0;
		if(authCheck > 0) insCnt =authService.updateUserAddAuth(parameterMap.getMap());
		return insCnt;
	}
	
	
	@DeleteMapping("/subuser-add")
	public Object deleteSubUserAddAuth(@AuthenticationPrincipal ScvmUserDetailsImpl scvmUserDetailsImpl, String authCd, ParameterMap parameterMap) {
		String pUserId = (String) parameterMap.get("pUserId");
		String pAuthCd = (String) parameterMap.get("pAuthCd");
		int authCheck = authService.getRoleCheck(scvmUserDetailsImpl.getUserId(),(String)  parameterMap.get("pAuthCd"));
		int delCnt = 0;
		if(authCheck > 0) delCnt = authService.deleteUserAddAuth(pUserId, pAuthCd);
		return delCnt;
	}
	
	//Alarm Daesang Controller
	@GetMapping({"/user-add/daesang-user"})
	public List<Map<String, Object>> getUserAddDaesangList(ParameterMap parameterMap) {
		String pAuthCd = (String) parameterMap.get("pAuthCd");
		String pUserGroupGb = (String) parameterMap.get("pUserGroupGb");
		String pUserStatusGb = (String) parameterMap.get("pUserStatusGb");
		String pSearchArgv = (String) parameterMap.get("pSearchArgv");
		String pSearchDeptNm = (String) parameterMap.get("pSearchDeptNm");
			
		List<Map<String, Object>> list = authService.getUserAddDaesangList(pAuthCd, pUserGroupGb, pUserStatusGb, pSearchArgv, pSearchDeptNm);
		return list;
	}
	
	@PostMapping("/widget-auth")
	public Object insertWidgetAuth(ParameterMap parameterMap) {
		int insCnt = authService.insertWidgetAuth(parameterMap.getMap());
		return insCnt;
	}
	
	@DeleteMapping("/widget-auth")
	public Object deleteWidgetAuth(ParameterMap parameterMap) {
		String pAppId = (String) parameterMap.get("pAppId");
		String pWidgetSeq = (String) parameterMap.get("pWidgetSeq");
		String pAuthCd = (String) parameterMap.get("pAuthCd");
		

		int delCnt = authService.deleteWidgetAuth(pAppId, pWidgetSeq, pAuthCd);
		return delCnt;
	}
	
	/**
	 * IP접근제한관리
	 */
	/* 접근허용 IP 리스트 조회 */
	@GetMapping("/ip-restricted-access-mng")
	public List<Map<String, Object>> selectIpRestrictedAccessMngList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = authService.selectIpRestrictedAccessMngList(parameterMap.getMap());
		return list;
	}
	
	/* 접근허용 IP 삽입 */
	@PostMapping("/ip-restricted-access-mng")
	public Object insertMileageoperationmng(ParameterMap parameterMap) {
		int insCnt = authService.insertMileageoperationmng(parameterMap.getMap());
		return insCnt;
	}
	
	/* 접근허용 IP 삭제 */
	@DeleteMapping("/ip-restricted-access-mng/{pUserId}/{pAllowIp}")
	public Object deleteMileageoperationmng(@PathVariable("pUserId") String pUserId
			                              , @PathVariable("pAllowIp") String pAllowIp
			                              , ParameterMap parameterMap) {
		parameterMap.put("USER_ID", pUserId); /* 사용자 ID */
		parameterMap.put("ALLOW_IP", pAllowIp); /* 허용 IP */
		
		int delCnt =  authService.deleteMileageoperationmng(parameterMap.getMap());
		return delCnt;
	}
}
