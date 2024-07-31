package kr.co.codefarm.svcm.systemmanager.corp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.corp.service.CorpService;


/**
 * @Author		: wjjoo
 * @Date		: 2024. 4. 1.
 * @Description	: 기업, 기관 관리 
 */
@RestController
@RequestMapping(value = "/system-manager/corp")
public class CorpController {
	
	@Autowired
	private CorpService corpService;
	
	@GetMapping("")
	public List<Map<String, Object>> getCropList(ParameterMap parameterMap){
		return corpService.getCorpList(parameterMap.getMap());
	}
	
	@GetMapping("/auth")
	public List<Map<String, Object>> getAuthList(ParameterMap parameterMap){
		return corpService.getAuthList(parameterMap.getMap());
	}
	
	@PostMapping("/auth")
	public Object insertAuth(ParameterMap parameterMap){
		return corpService.insertAuth(parameterMap.getMap());
	}
	
	@DeleteMapping("/auth")
	public Object deleteAuth(ParameterMap parameterMap){
		return corpService.deleteAuth(parameterMap.getMap());
	}
	
	@GetMapping("/corp-auth")
	public List<Map<String, Object>> getCorpAuthList(ParameterMap parameterMap){
		return corpService.getCorpAuthList(parameterMap.getMap());
	}
	
	@PutMapping("/corp-user-auth")
	public Object updateCorpUserAuth(ParameterMap parameterMap) {
		return corpService.updateCorpUserAuth(parameterMap.getMap());
	}
}
