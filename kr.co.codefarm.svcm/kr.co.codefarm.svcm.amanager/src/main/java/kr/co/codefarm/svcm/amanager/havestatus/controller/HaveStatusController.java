/**
 * 
 */
package kr.co.codefarm.svcm.amanager.havestatus.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.codefarm.svcm.amanager.havestatus.service.HaveStatusService;
import kr.co.codefarm.svcm.commons.map.ParameterMap;

/**
 * @author 
 *
 */
@RestController
@RequestMapping(value = "/amanager/havestatus")
public class HaveStatusController {

	@Autowired
	private HaveStatusService HaveStatusService;
	
	// 시스템 관리 > 로그 관리 > 로그 목록 조회 
	@GetMapping("/havestatus-list")
	public List<Map<String, Object>> getHaveStatusList(ParameterMap parameterMap) {
		return  HaveStatusService.getHaveStatusList(parameterMap.getMap());
	}
	
	//보유 현황 수정
	@PutMapping("/updateHave")
	public Object updateService(ParameterMap parameterMap) {
		int insCnt = HaveStatusService.updateHaveStatus(parameterMap.getMap());
			return insCnt;
	}
	
	//태그 발급 요청
	@PostMapping("/insertTa")
	public Object  InsertTaService(ParameterMap parameterMap) {
		int insCnt = HaveStatusService.insertTaPrtIssuReq(parameterMap.getMap());
		return insCnt;
	}
	
	//태그 발급 취소
	@PutMapping("/updatecnclTa")
	public Object  updatecnclTaService(ParameterMap parameterMap) {
		int insCnt = HaveStatusService.updateTaPrtIsuuReqcncl(parameterMap.getMap());
		return insCnt;
	}
	
	//사용 위치 조회
	@GetMapping("/useloc-list")
	public List<Map<String, Object>> getUseLoc(ParameterMap parameterMap) {
		return  HaveStatusService.getUseLoc(parameterMap.getMap());
	}
	
	
	
}
