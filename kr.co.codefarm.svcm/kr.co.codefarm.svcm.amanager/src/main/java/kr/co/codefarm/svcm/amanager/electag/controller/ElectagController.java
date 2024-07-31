package kr.co.codefarm.svcm.amanager.electag.controller;
import kr.co.codefarm.svcm.commons.map.ParameterMap;

import org.springframework.beans.factory.annotation.Autowired;
import kr.co.codefarm.svcm.amanager.electag.service.ElectagService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 
 *
 */
@RestController
@RequestMapping(value = "/amanager/electag")
public class ElectagController {

	@Autowired
	private ElectagService ElectagService;
	
	
	// 시스템 관리 > 로그 관리 > 로그 목록 조회 
	@GetMapping("/elecReq-list")
	public List<Map<String, Object>> getElecTagReqStatusList(ParameterMap parameterMap) {
		return ElectagService.getElecTagReqStatusList(parameterMap.getMap());
	}
	
	@GetMapping("/elecIssu-list")
	public List<Map<String, Object>> getElecTagIssueStatusList(ParameterMap parameterMap) {
		return ElectagService.getElecTagIssueStatusList(parameterMap.getMap());
	}
	
	@GetMapping("/elecIssuDetl-list")
	public List<Map<String, Object>> getElecTagIssueStatusDetlQry(ParameterMap parameterMap) {
		return ElectagService.getElecTagIssueStatusDetlQry(parameterMap.getMap());
	}
	
	
	//태그발급 업데이트
	@PutMapping("/updateTa")
	public Object updateTaPrtIssu(ParameterMap parameterMap) {
		int insCnt = ElectagService.updateTaPrtIssu(parameterMap.getMap());
		return insCnt;
	}
	
}
