/** 
 * 
 */
package kr.co.codefarm.svcm.systemmanager.menu.controller;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.menu.service.MenuApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author AYJ
 *
 */
@RestController
@RequestMapping(value = "/api/system-manager/menu")
public class MenuApiController {

	@Autowired
	private MenuApiService menuApiService;

	// 사용자 앱 검색
	@GetMapping({"/user-auth-apps"})
	public List<Map<String, Object>> getAuthAppList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String userDefaultAuthCd = (String) userDetailsImpl.getDefaultAuthCd();
		List<Map<String, Object>> list = menuApiService.getAuthAppList(parameterMap.getMap(), userDefaultAuthCd);
		return list;
	}
	// 메뉴 검색
	@GetMapping({"/user-auth-menus"})
	public List<Map<String, Object>> getAuthMenuList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String userDefaultAuthCd = (String) userDetailsImpl.getDefaultAuthCd();
		List<Map<String, Object>> list = menuApiService.getAuthMenuList(parameterMap.getMap(), userDefaultAuthCd);
		return list;
	}
	
	// 매뉴얼 검색
	@GetMapping({"/{appSeq}/{menuSeq}"})
	public List<Map<String, Object>> getAppMenuManualList(@PathVariable(value="appSeq") String appSeq, @PathVariable(value="menuSeq") String menuSeq, ParameterMap parameterMap) {
		parameterMap.put("APP_ID", appSeq);
		parameterMap.put("MENU_CD", menuSeq);
		
		List<Map<String, Object>> appMenuManualList = menuApiService.getAppMenuManualList(parameterMap.getMap());
		
		return appMenuManualList;
	}
}
