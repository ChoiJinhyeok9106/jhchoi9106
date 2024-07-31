
package kr.co.codefarm.svcm.systemmanager.auth.controller;


import kr.co.codefarm.svcm.commons.annotation.ManagedAPI;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.auth.service.AuthApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
/**
 * @author CDG
 */

@ManagedAPI
@RestController
@RequestMapping(value = "/api/system-manager/auth")
public class AuthApiController {

	@Autowired
	private AuthApiService authApiService;
	
	/* 미인증된 사용자 조회 */
	@GetMapping("/notAuthList")
	public List<Map<String, Object>> getNotAuthList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = authApiService.getNotAuthList(parameterMap.getMap());
		return list;
	}
	
	@GetMapping("/getAllAuthList")
	public List<Map<String, Object>> getAllAuthList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = authApiService.getAllAuthList(parameterMap.getMap());
		return list;
	}

	@GetMapping("/sangdamsa")
	public List<Map<String, Object>> getSangdamsaList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = authApiService.getSangdamsaList(parameterMap.getMap());
		return list;
	}
	
	@GetMapping("/baejeong-auth")
	public List<Map<String, Object>> getBaejeongAuthList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = authApiService.getBaejeongAuthList(parameterMap.getMap());
		return list;
	}
	
	/* 인증산업체 업데이트 */
	@PutMapping("/updateCompany")
	public Object updateCompany(ParameterMap parameterMap) {
		int updCnt = authApiService.updateCompany(parameterMap.getMap());
		return updCnt;
	}
	
	/* 산업체 권한 처리 */
	@PutMapping("/update-company-auth")
	public Object updateCompanyAuth(ParameterMap parameterMap) {
		int updCnt = authApiService.updateCompanyAuth(parameterMap.getMap());
		return updCnt;
	}
	
	/* 체크된 회사만 인증산업체 업데이트 */
	@PutMapping("/updateCompanyChk")
	public Object updateCompanyChk(ParameterMap parameterMap) {
		int updCnt = 0;
		//List<Map<String, Object>> list  = parameterMap.get("data");
		//int updCnt = userApiService.updateCompany(parameterMap.getMap());
		List list = (List) parameterMap.get("data");
		
		for(int i = 0 ; i < list.size() ; i++) {
			Map companyInfo = (Map<String, Object>) list.get(i);
			
			companyInfo.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
			companyInfo.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
			companyInfo.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
			
			updCnt = authApiService.updateCompany(companyInfo);
		}
		
		return updCnt;
	}
	
	
	/* 인증상담사 업데이트 */
	@PutMapping("/updateSangdamsa")
	public Object updateSandanmsa(ParameterMap parameterMap) {
		int updCnt = authApiService.updateSangdamsa(parameterMap.getMap());
		return updCnt;
	}
	
	/* 체크된 상담사만 인증상담사 업데이트 */
	@PutMapping("/updateSangdamsaChk")
	public Object updateSandanmsaChk(ParameterMap parameterMap) {
		int updCnt = 0;
		//List<Map<String, Object>> list  = parameterMap.get("data");
		//int updCnt = userApiService.updateCompany(parameterMap.getMap());
		List list = (List) parameterMap.get("data");
		
		for(int i = 0 ; i < list.size() ; i++) {
			Map companyInfo = (Map<String, Object>) list.get(i);
			
			companyInfo.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
			companyInfo.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
			companyInfo.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
			
			updCnt = authApiService.updateSangdamsa(companyInfo);
		}
		return updCnt;
	}
	
	
	
	/* 인증된 비교과강사 조회 */
	@GetMapping("/approvalGangsaList")
	public List<Map<String, Object>> getApprovalGangsaList(ParameterMap parameterMap) {			
		List<Map<String, Object>> list = authApiService.getApprovalGangsaList(parameterMap.getMap());
		return list;
	}
	
	/* 인증산업체 업데이트 */
	@PutMapping("/updateNcuGangsa")
	public Object updateNcuGangsa(ParameterMap parameterMap) {
		int updCnt = authApiService.updateGangsa(parameterMap.getMap());
		return updCnt;
	}
	
	
	/* 체크된 비교과 강사만 인증비교과 강사 업데이트 */
	@PutMapping("/updateNcuGangsaChk")
	public Object updateNcuGangsaChk(ParameterMap parameterMap) {
		int updCnt = 0;
		//List<Map<String, Object>> list  = parameterMap.get("data");
		//int updCnt = userApiService.updateCompany(parameterMap.getMap());
		List list = (List) parameterMap.get("data");
		
		for(int i = 0 ; i < list.size() ; i++) {
			Map gangsaInfo = (Map<String, Object>) list.get(i);
			
			gangsaInfo.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
			gangsaInfo.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
			gangsaInfo.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
			
			updCnt = authApiService.updateGangsa(gangsaInfo);
		}
		
		return updCnt;
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
		
		List<Map<String, Object>> list = authApiService.getUserAddAuthList(pAuthCd, pUserId, pSearchType, pSearchArgv, pPageNo, pRowCount);
		return list;
	}
	
	/* 사용자의 메뉴별 권한 조회 */
	@GetMapping("/user-auth")
	public List<Map<String, Object>> selectUserAuth(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl
			                                      , ParameterMap parameterMap) {
		parameterMap.put("pUserId", userDetailsImpl.getInternalId()); /* 대상자 아이디 */
		List<Map<String, Object>> list = authApiService.selectUserAuth(parameterMap.getMap());
		return list;
	}
}
