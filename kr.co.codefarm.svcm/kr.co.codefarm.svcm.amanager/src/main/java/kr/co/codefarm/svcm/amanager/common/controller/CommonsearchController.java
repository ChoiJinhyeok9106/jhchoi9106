/**
 * 
 */
package kr.co.codefarm.svcm.amanager.common.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;

import org.springframework.beans.factory.annotation.Autowired;
import kr.co.codefarm.svcm.amanager.common.service.CommonsearchService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 
 *
 */
@RestController
@RequestMapping(value = "/amanager/common")
public class CommonsearchController {

	@Autowired
	private CommonsearchService CommonsearchService;
	
	
	
	@GetMapping("/dept-list")
	public List<Map<String, Object>> getDeptList(ParameterMap parameterMap) {
		return CommonsearchService.getDeptList(parameterMap.getMap());
	}
	
	@GetMapping("/assetno-list")
	public List<Map<String, Object>> getAsstNoList(ParameterMap parameterMap) {
		return CommonsearchService.getAsstNoList(parameterMap.getMap());
	}
	
	@GetMapping("/usr-list")
	public List<Map<String, Object>> getUsrList(ParameterMap parameterMap) {
		return CommonsearchService.getUsrList(parameterMap.getMap());
	}
	
	@GetMapping("/depttree-list")
	public List<Map<String, Object>> getDeptTreeList(ParameterMap parameterMap) {
		return CommonsearchService.getDeptTreeList(parameterMap.getMap());
	}
	
	
}
