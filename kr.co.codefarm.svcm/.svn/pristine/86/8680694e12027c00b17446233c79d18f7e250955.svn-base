/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.layout.controller;

import kr.co.codefarm.svcm.commons.controller.BaseController;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.layout.service.LayoutService;
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
@RequestMapping(value = "/system-manager/layout")
public class LayoutController extends BaseController {
	
	@Autowired
	private LayoutService layoutService;
	
	@GetMapping({""})
	public List<Map<String, Object>> getLayoutList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		String pDefaultAuthCd = (String) userDetailsImpl.getDefaultAuthCd();
		List<Map<String, Object>> list = layoutService.getLayoutList(pUserId, pDefaultAuthCd, parameterMap.getMap());
		return list;
	}
	
	@PostMapping("")
	public Object insertLayout(ParameterMap parameterMap) {
		Map<String, Object> insObj = layoutService.insertLayout(parameterMap.getMap());
		return insObj;
	}
	
	@PutMapping("")
	public Object updateLayout(ParameterMap parameterMap) {
		int insCnt = layoutService.updateLayout(parameterMap.getMap());
		return insCnt;
	}
	
	@DeleteMapping("")
	public Object deleteLayout(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) parameterMap.get("pUserId");
		String pUserPageSeq = (String) parameterMap.get("pUserPageSeq");
		int delCnt = layoutService.deleteLayout(pUserId, pUserPageSeq);
		return delCnt;
	}
	
	@GetMapping({"/content"})
	public List<Map<String, Object>> getLayoutContentList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = layoutService.getLayoutContentList(parameterMap.getMap());
		return list;
	}
	
	@PostMapping("/content")
	public int insertLayoutContent(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		int insCnt = layoutService.insertLayoutContent(pUserId, parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("/content")
	public Object updateLayoutContent(ParameterMap parameterMap) {
		int insCnt = layoutService.updateLayoutContent(parameterMap.getMap());
		return insCnt;
	}
	
	@DeleteMapping("/content")
	public Object deleteLayoutContent(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		String pUserPageSeq = (String) parameterMap.get("pUserPageSeq");
		String pLayoutContentSeq = (String) parameterMap.get("pLayoutContentSeq");
		int delCnt = layoutService.deleteLayoutContent(pUserId, pUserPageSeq, pLayoutContentSeq);
		return delCnt;
	}
	@GetMapping("/popup-count")
	public List<Map<String, Object>> getPopupCount(ParameterMap parameterMap) {
		List<Map<String, Object>> list = layoutService.getPopupCount(parameterMap.getMap());
		return list;
	}

}
