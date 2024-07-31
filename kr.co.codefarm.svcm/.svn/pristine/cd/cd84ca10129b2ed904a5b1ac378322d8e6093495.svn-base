/**
 * 
 */
package kr.co.codefarm.svcm.commons.controller.cors;

import kr.co.codefarm.svcm.commons.controller.BaseController;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/commons/cors")
public class CorsXmlController extends BaseController {
	@PostMapping({"/xml"})
	public Object corsCallBackXml(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		List<Map<String, Object>> dataParameter = (List<Map<String, Object>>) parameterMap.get("DATA_PARAM_LIST");
		String callUrl = (String) parameterMap.get("CALL_URL");
		
		if(dataParameter != null && dataParameter.size() > 0) {
			for (Map<String, Object> mapItem : dataParameter) {
				for (String key : mapItem.keySet()) {
					if(callUrl.indexOf("${"+key+"}") > -1) {
						callUrl = callUrl.replaceAll("\\$\\{"+key+"\\}", (String) mapItem.get(key));
					}
				}
			}
		}
		
		return this.get(callUrl,"xml");
	}
	
	@PostMapping({"/json"})
	public Object corsCallBackJson(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		List<Map<String, Object>> dataParameter = (List<Map<String, Object>>) parameterMap.get("DATA_PARAM_LIST");
		String callUrl = (String) parameterMap.get("CALL_URL");
		
		if(dataParameter != null && dataParameter.size() > 0) {
			for (Map<String, Object> mapItem : dataParameter) {
				for (String key : mapItem.keySet()) {
					if(callUrl.indexOf("${"+key+"}") > -1) {
						callUrl = callUrl.replaceAll("\\$\\{"+key+"\\}", (String) mapItem.get(key));
					}
				}
			}
		}
		return this.get(callUrl,"json");
	}
	
	@GetMapping({"/xml"})
	public Object corsCallBackXmlGet(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		List<Map<String, Object>> dataParameter = (List<Map<String, Object>>) parameterMap.get("DATA_PARAM_LIST");
		String callUrl = (String) parameterMap.get("CALL_URL");
		
		if(dataParameter != null && dataParameter.size() > 0) {
			for (Map<String, Object> mapItem : dataParameter) {
				for (String key : mapItem.keySet()) {
					if(callUrl.indexOf("${"+key+"}") > -1) {
						callUrl = callUrl.replaceAll("\\$\\{"+key+"\\}", (String) mapItem.get(key));
					}
				}
			}
		}
		
		return this.get(callUrl,"xml");
	}
	
	@GetMapping({"/json"})
	public Object corsCallBackJsonGet(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		List<Map<String, Object>> dataParameter = (List<Map<String, Object>>) parameterMap.get("DATA_PARAM_LIST");
		String callUrl = (String) parameterMap.get("CALL_URL");
		
		if(dataParameter != null && dataParameter.size() > 0) {
			for (Map<String, Object> mapItem : dataParameter) {
				for (String key : mapItem.keySet()) {
					if(callUrl.indexOf("${"+key+"}") > -1) {
						callUrl = callUrl.replaceAll("\\$\\{"+key+"\\}", (String) mapItem.get(key));
					}
				}
			}
		}
		return this.get(callUrl,"json");
	}
}
