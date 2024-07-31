/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.defaultinfo.controller;

import kr.co.codefarm.svcm.commons.map.DefaultInfoMap;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.defaultinfo.service.DefaultInfoApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CDG
 *
 */
@RestController
@RequestMapping(value = "/api/system-manager/default-info")
public class DefaultInfoApiController {

	@Autowired
	private DefaultInfoApiService defaultInfoApiService;
	
	/* 이용약관(작성자 : 최대건) */
	@GetMapping("/defaultinfo")
	public Map<String, Object> getDefaultinfo(ParameterMap parameterMap) {
//		List<Map<String, Object>> defaultinfo = defaultInfoApiService.getDefaultinfo();
//		return defaultinfo;
		return DefaultInfoMap.getMap();
	}
	
	/* 기본정보조회 */
	@GetMapping("/info")
	public List<Map<String, Object>> getInfo(ParameterMap parameterMap) {
		List<Map<String, Object>> defaultinfo = defaultInfoApiService.getInfo(parameterMap.getMap());
		return defaultinfo;
	}

	@GetMapping("/service")
	public List<Map<String, Object>> getServiceTerms(ParameterMap parameterMap) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BOARD_SEQ","4");
		
		List<Map<String, Object>> list = defaultInfoApiService.getTerms(paramMap);
		return list;
	}
	
	@GetMapping("/privacy")
	public List<Map<String, Object>> getPrivacyTerms(ParameterMap parameterMap) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BOARD_SEQ","5");
		
		List<Map<String, Object>> list = defaultInfoApiService.getTerms(paramMap);
		return list;
	}
	
	@GetMapping("/last")
	public List<Map<String, Object>> getLastTerms(ParameterMap parameterMap) {
		Map<String, Object> paramMap = new HashMap<>();
		
		List<Map<String, Object>> list = defaultInfoApiService.getLastTerms(paramMap);
		return list;
	}	
}
