/**
 * 
 */
package kr.co.codefarm.svcm.additional.calendar.controller;

import kr.co.codefarm.svcm.additional.calendar.service.CalendarApiService;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author KKC
 *
 */
@RestController
@RequestMapping(value = "/api/additional/calendar")
public class CalendarApiController {

	@Autowired
	private CalendarApiService calendarApiService;
	
	@GetMapping("/calendar")
	public List<Map<String, Object>> getCalendarList(ParameterMap parameterMap) {
		return calendarApiService.getCalendarList(parameterMap.getMap());
	}
	
	@GetMapping("/swc-calendar")
	public List<Map<String, Object>> getSWCSelectCalendarList(ParameterMap parameterMap) {
		parameterMap.put("INTERNAL_ID", "0000001");
		parameterMap.put("TYPE", "SWC");
		
		//아래의 selectcalendar와 같은 service를 호출함
		return calendarApiService.selectCalendarList(parameterMap.getMap());
	}

	@GetMapping("/selectcalendar")
	public List<Map<String, Object>> selectCalendarList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		parameterMap.put("INTERNAL_ID", userDetailsImpl.getInternalId());
		parameterMap.put("TYPE", "NOT-SWC");
		
		return calendarApiService.selectCalendarList(parameterMap.getMap());
	}
	
}
