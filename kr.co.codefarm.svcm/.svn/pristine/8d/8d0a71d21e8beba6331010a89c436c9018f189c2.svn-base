/**
 * 
 */
package kr.co.codefarm.svcm.additional.calendar.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.codefarm.svcm.additional.calendar.service.CalendarService;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;

/**
 * @author KKC
 *
 */
@RestController
@RequestMapping(value = "/additional/calendar")
public class CalendarController {

	@Autowired
	private CalendarService calendarService;
	
	//캘린더 (ADD_CAL_ENV)
	//캘린더 전체 조회
	@GetMapping({""})
	public List<Map<String, Object>> getCalendarList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		String pUserId = userDetailsImpl.getInternalId();
			
		List<Map<String, Object>> list = calendarService.getCalendarList(pUserId);
		return list;
	}
		
	@GetMapping("/cal")
	public Object getCalendar(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		String pUserId = (String) userDetailsImpl.getInternalId();
		String pCalSeq = (String) parameterMap.get("pCalSeq");
		
		Object cal = calendarService.getCalendar(pUserId, pCalSeq);
		return cal;
	}

		
	@PostMapping("")
	public Object insertCalendar(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
		Map<String, Object> insObj = calendarService.insertCalendar(parameterMap.getMap());
		return insObj;
	}
		
	@PutMapping("")
	public Object updateCalendar(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
		int insCnt = calendarService.updateCalendar(parameterMap.getMap());
		return insCnt;
	}
		
	@DeleteMapping("")
	public Object deleteCalendar(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		String pUserId = (String) userDetailsImpl.getInternalId();
		String pCalSeq = (String) parameterMap.get("pCalSeq");
			
		int delCnt = calendarService.deleteCalendar(pUserId, pCalSeq);
		return delCnt;
	}
	
	
	
	
	//캘린더 (ADD_CAL_EVENT)
	//캘린더 전체 조회
	@GetMapping({"/event"})
	public List<Map<String, Object>> getCalEventList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		
		String pUserId = (String) userDetailsImpl.getInternalId();
				
		List<Map<String, Object>> list = calendarService.getCalEventList(pUserId);
		return list;
	}
			
	@GetMapping("/event/cal")
	public Object getCalEvent(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		String pUserId = (String) userDetailsImpl.getInternalId();
		String pIljeongSeq = (String) parameterMap.get("pIljeongSeq");
		
		Object cal = calendarService.getCalEvent(pUserId, pIljeongSeq);
		return cal;
	}
			
			
	@PostMapping("/event")
	public Object insertCalEvent(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
		Map<String, Object> insObj = calendarService.insertCalEvent(parameterMap.getMap());
		return insObj;
	}
			
	@PutMapping("/event")
	public Object updateCalEvent(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
		int insCnt = calendarService.updateCalEvent(parameterMap.getMap());
		return insCnt;
	}
			
	@DeleteMapping("/event")
	public Object deleteCalEvent(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		String pUserId = (String) userDetailsImpl.getInternalId();
		String pIljeongSeq = (String) parameterMap.get("pIljeongSeq");
				
		int delCnt = calendarService.deleteCalEvent(pUserId, pIljeongSeq);
		return delCnt;
	}
}
