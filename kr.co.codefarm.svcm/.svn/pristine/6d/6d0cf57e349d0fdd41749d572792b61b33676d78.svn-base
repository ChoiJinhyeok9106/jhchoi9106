/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.log.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/system-manager/log")
public class LogController {

	@Autowired
	private LogService LogService;
	
	// 시스템 관리 > 로그 관리 > 로그 목록 조회 
	@GetMapping("/log-list")
	public List<Map<String, Object>> getLogList(ParameterMap parameterMap) {
		return LogService.getLogList(parameterMap.getMap());
	}

	// 시스템 관리 > 로그 관리 > 로그 통계 조회
	@GetMapping("/log-statistics")
	public List<Map<String, Object>> getLogTonggye(ParameterMap parameterMap) {
		return LogService.getLogTonggye(parameterMap.getMap());
	}

	// 시스템 관리 > 로그 관리 > 접속자 수
	@GetMapping("/jeopsokja")
	public List<Map<String, Object>> getUsersLogList(ParameterMap parameterMap) {
		return LogService.getUsersLogList(parameterMap.getMap());
	}

	// 시스템 관리 > 로그 관리 > 게시판 이용자
	@GetMapping("/boards")
	public List<Map<String, Object>> getBoardsLogList(ParameterMap parameterMap) {
		return LogService.getBoardsLogList(parameterMap.getMap());
	}
	
}
