package kr.co.codefarm.svcm.systemmanager.app.controller;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.app.service.AppApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/api/system-manager/app")
public class AppApiController {
	@Autowired
	private AppApiService appApiService;

	/*get apps*/
	@GetMapping({""})
	public List<Map<String, Object>> getAppList(ParameterMap parameterMap) {
		String pSearchType = (String) parameterMap.get("pSearchType");
		String pSearchArgv = (String) parameterMap.get("pSearchArgv");
		String pPageNo = (String) parameterMap.get("pPageNo");
		String pRowCount = (String) parameterMap.get("pRowCount");
		
		List<Map<String, Object>> list = appApiService.getAppList(pSearchType, pSearchArgv, pPageNo, pRowCount);
		return list;
	}
	
	/*get app*/
	@GetMapping("/{appSeq}")
	public Object getApp(@PathVariable(value="appSeq") String appSeq, ParameterMap parameterMap) {
//		if(appSeq.isEmpty()) {
//			/* appSeq is not null*/
//		}
		
		Object app = appApiService.getApp(appSeq);
		return app;
	}
	
	/*get subscribe-apps*/
	@GetMapping("/subscribe-apps")
	public List<Map<String, Object>> subscribeApps(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pFixedAuthCd = (String) parameterMap.get("FIXED_AUTH_CD");
		String pUserId = userDetailsImpl.getUserId();
		String pAuthCd = (String) userDetailsImpl.getDefaultAuthCd();
		
		if(pFixedAuthCd != null && !pFixedAuthCd.equals("")) {
			pUserId = "";
			pAuthCd = pFixedAuthCd;
		}
		
		List<Map<String, Object>> list = appApiService.getSubscribeAppList(pUserId, pAuthCd);
		return list;
	}
}
