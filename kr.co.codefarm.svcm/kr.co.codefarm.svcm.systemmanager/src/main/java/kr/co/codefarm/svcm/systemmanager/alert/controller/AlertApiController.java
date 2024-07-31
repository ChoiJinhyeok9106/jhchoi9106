/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.alert.controller;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.alert.service.AlertApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequestMapping(value = "/api/system-manager/alert")
public class AlertApiController {

	@Autowired
	private AlertApiService alertApiService;

	//Alarm Controller
	@GetMapping("/user/alarm/unidentified-alarms")
	public List<Map<String, Object>> getUserUnidentifiedAlarmDataList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		List<Map<String, Object>> list = alertApiService.getUserUnidentifiedAlarmDataList(pUserId);
		return list;
	}

	//Alarm Controller
	@GetMapping("/user/alarm/unidentified-cnt")
	public int getUnidentifiedAlarmDataCnt(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pUserId = (String) userDetailsImpl.getUserId();
		int cnt = alertApiService.getUnidentifiedAlarmDataCnt(pUserId);
		return cnt;
	}
}
