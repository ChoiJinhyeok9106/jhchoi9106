/**
 * 
 */
package kr.co.codefarm.svcm.amanager.havestatushist.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;

import org.springframework.beans.factory.annotation.Autowired;
import kr.co.codefarm.svcm.amanager.havestatushist.service.HavestatushistService;

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
@RequestMapping(value = "/amanager/havestatushist")
public class HavestatushistController {

	@Autowired
	private HavestatushistService HavestatushistService;
	
	
	// 시스템 관리 > 로그 관리 > 로그 목록 조회 
	@GetMapping("/hist-list")
	public List<Map<String, Object>> getHaveStatusHistList(ParameterMap parameterMap) {
		return HavestatushistService.getHaveStatusHistList(parameterMap.getMap());
	}
	
}
