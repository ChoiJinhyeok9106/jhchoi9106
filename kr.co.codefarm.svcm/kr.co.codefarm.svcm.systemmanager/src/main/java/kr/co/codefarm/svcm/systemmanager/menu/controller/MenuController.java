/** 
 * 
 */
package kr.co.codefarm.svcm.systemmanager.menu.controller;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/system-manager/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	//Menu Controller
	@GetMapping({""})
	public List<Map<String, Object>> getMenuList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = menuService.getMenuList(parameterMap.getMap());
		return list;
	}

	//Menu Controller
	@GetMapping({"/user-auth-menus"})
	public List<Map<String, Object>> getAuthMenuList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String userDefaultAuthCd = (String) userDetailsImpl.getDefaultAuthCd();
		List<Map<String, Object>> list = menuService.getAuthMenuList(parameterMap.getMap(), userDefaultAuthCd);
		return list;
	}
	
	@PostMapping("")
	public Object insertMenu(ParameterMap parameterMap) {
		Map<String, Object> insObj = menuService.insertMenu(parameterMap.getMap());
		return insObj;
	}
	
	
	@PutMapping("")
	public Object updateMenu(String menuSeq, ParameterMap parameterMap) {
		int insCnt = menuService.updateMenu(parameterMap.getMap());
		return insCnt;
	}
	
	
	@DeleteMapping("")
	public Object deleteMenu(ParameterMap parameterMap) {
		int delCnt = menuService.deleteMenu(parameterMap.getMap());
		return delCnt;
	}
	
	
	//MenuAuth Controller
	@GetMapping({"/auths"})
	public List<Map<String, Object>> getMenuAuthList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = menuService.getMenuAuthList(parameterMap.getMap());
		return list;
	}
	
	@PostMapping("/auth")
	public Object insertMenuAuth(ParameterMap parameterMap) {
		Map<String, Object> insObj = menuService.insertMenuAuth(parameterMap.getMap());
		return insObj;
	}
	
	
	@PutMapping("/auth")
	public Object updateMenuAuth(String menuSeq, ParameterMap parameterMap) {
		int insCnt = menuService.updateMenuAuth(parameterMap.getMap());
		return insCnt;
	}
	
	
	@DeleteMapping("/auth")
	public Object deleteMenuAuth(ParameterMap parameterMap) {
		int delCnt = menuService.deleteMenuAuth(parameterMap.getMap());
		return delCnt;
	}
	
	
	//MenuAuth Controller
	@GetMapping("/sub-pgm")
	public List<Map<String, Object>> getSubPgmList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = menuService.getSubPgmList(parameterMap.getMap());
		return list;
	}
	
	@PostMapping("/sub-pgm")
	public Object insertSubPgm(ParameterMap parameterMap) {
		Map<String, Object> insObj = menuService.insertSubPgm(parameterMap.getMap());
		return insObj;
	}
	
	
	@PutMapping("/sub-pgm")
	public Object updateSubPgm(String menuSeq, ParameterMap parameterMap) {
		int insCnt = menuService.updateSubPgm(parameterMap.getMap());
		return insCnt;
	}
	
	
	@DeleteMapping("/sub-pgm")
	public Object deleteSubPgm(ParameterMap parameterMap) {
		int delCnt = menuService.deleteSubPgm(parameterMap.getMap());
		return delCnt;
	}
	
	/*get app menu manual - yjahn 2019-08-07 */
	@GetMapping({"/{appSeq}/{menuSeq}"})
	public List<Map<String, Object>> getAppMenuManualList(@PathVariable(value="appSeq") String appSeq, @PathVariable(value="menuSeq") String menuSeq, ParameterMap parameterMap) {
		parameterMap.put("APP_ID", appSeq);
		parameterMap.put("MENU_CD", menuSeq);
		
		List<Map<String, Object>> appMenuManualList = menuService.getAppMenuManualList(parameterMap.getMap());
		
		return appMenuManualList;
	}
	
	/*insert app menu manual - yjahn 2019-08-08 */
	@PostMapping("/manual")
	public int insertAppMenuManual(ParameterMap parameterMap) {
		int saveCnt = menuService.insertAppMenuManual(parameterMap.getMap());
		return saveCnt;
	}
	
	/*update app menu manual - yjahn 2019-08-08 */
	@PutMapping("/manual")
	public int updateAppMenuManual(ParameterMap parameterMap) {
		int saveCnt = menuService.updateAppMenuManual(parameterMap.getMap());
		return saveCnt;
	}
	
	/*delete app menu manual - yjahn 2019-08-08 */
	@DeleteMapping("/manual/{pAppId}/{pMenuCd}")
	public Object deleteAppMenuManual(@PathVariable(value="pAppId") String pAppId, @PathVariable(value="pMenuCd") String pMenuCd, ParameterMap parameterMap) {
		int delCnt = menuService.deleteAppMenuManual(pAppId, pMenuCd);
		return delCnt;
	}

	/*insert app menu manual - yjahn 2019-08-08 */
	@PostMapping("/auth-batch")
	public int insertMenuAuthBatch(ParameterMap parameterMap) {
		int saveCnt = menuService.insertMenuAuthBatch(parameterMap.getMap());
		return saveCnt;
	}

	/*delete app menu manual - yjahn 2019-08-08 */
	@DeleteMapping("/auth-batch")
	public Object deleteMenuAuthBatch(ParameterMap parameterMap) {
		int delCnt = menuService.deleteMenuAuthBatch(parameterMap.getMap());
		return delCnt;
	}
}
