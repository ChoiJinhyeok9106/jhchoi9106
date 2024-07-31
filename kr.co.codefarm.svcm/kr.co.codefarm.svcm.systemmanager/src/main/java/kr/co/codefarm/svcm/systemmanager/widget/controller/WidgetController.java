/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.widget.controller;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.widget.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/system-manager/widget")
public class WidgetController {

	@Autowired
	private WidgetService widgetService;

	/*get widgets*/
	@GetMapping({""})
	public List<Map<String, Object>> getWidgetList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		String pSearchAppId = "";
		String pSearchType = "";
		String pSearchArgv = "";
		String pPageNo = "";
		String pRowCount = "";
		
		if(parameterMap.containsKey("pSearchAppId")) {
			pSearchAppId = parameterMap.get("pSearchAppId").toString();
		}

		if(parameterMap.containsKey("pSearchType")) {
			pSearchType = parameterMap.get("pSearchType").toString();
		}
		
		if(parameterMap.containsKey("pSearchArgv")) {
			pSearchArgv = parameterMap.get("pSearchArgv").toString();
		}

		if(parameterMap.containsKey("pPageNo")) {
			pPageNo = parameterMap.get("pPageNo").toString();
		}
		
		
		if(parameterMap.containsKey("pRowCount")) {
			pRowCount = parameterMap.get("pRowCount").toString();
		}

		String pUserId = userDetailsImpl.getUserId();
		String pAuthCd = userDetailsImpl.getDefaultAuthCd();
		
		List<Map<String, Object>> list = widgetService.getWidgetList(pSearchAppId, pSearchType, pSearchArgv, pPageNo, pRowCount, pUserId, pAuthCd);
		return list;
	}
	
	/*get app*/
	@GetMapping("/{widgetSeq}")
	public Object getWidget(@PathVariable(value="widgetSeq") String widgetSeq, ParameterMap parameterMap) {
		String pAppId = (String) parameterMap.get("pAppId");
		
//		if(widgetSeq.isEmpty()) {
//			/* widgetSeq is not null*/
//		}
		
		Object widget = widgetService.getWidget(pAppId, widgetSeq);
		return widget;
	}
	
	/*insert widget*/
	@PostMapping("")
	public Object insertWidget(ParameterMap parameterMap) {
		Map<String, Object> widgetRow = widgetService.insertWidget(parameterMap.getMap());
		return widgetRow;
	}
	
	/*update widget*/
	@PutMapping("")
	public Object updateWidget(ParameterMap parameterMap) {
		int insCnt = widgetService.updateWidget(parameterMap.getMap());
		return insCnt;
	}
	
	/*delete widget*/
	@DeleteMapping("/{widgetSeq}")
	public Object deleteWidget(@PathVariable(value="widgetSeq") String widgetSeq, ParameterMap parameterMap) {
		String pAppId = (String) parameterMap.get("pAppId");
//		if(widgetSeq.isEmpty()) {
//			/* widgetId is not null*/
//		}
		int delCnt = widgetService.deleteWidget(pAppId, widgetSeq);
		return delCnt;
	}
	
	//위젯 권한 리스트(작성자 : 최대건(2019/06/10))
	@GetMapping("/widget-auth")
	public List<Map<String, Object>> getAuthWidget(ParameterMap parameterMap) {
		String pAppUseYn = parameterMap.get("pAppUseYn").toString();
		String pSearchType = parameterMap.get("pSearchType").toString();
		String pSearchArgv = parameterMap.get("pSearchArgv").toString();
		String pAuthCd = parameterMap.get("pAuthCd").toString();
		String pPageNo = parameterMap.get("pPageNo").toString();
		String pRowCount = parameterMap.get("pRowCount").toString();
		
		List<Map<String, Object>> list = widgetService.getAuthWidget(pAppUseYn, pSearchType, pSearchArgv, pAuthCd, pPageNo, pRowCount);
		return list;
	}
}
