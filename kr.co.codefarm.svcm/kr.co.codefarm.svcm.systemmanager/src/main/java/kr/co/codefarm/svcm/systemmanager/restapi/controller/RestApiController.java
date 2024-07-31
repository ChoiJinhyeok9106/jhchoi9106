/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.restapi.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.restapi.service.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author YJAHN
 *
 */
@RestController
@RequestMapping(value = "/system-manager/rest-api")
public class RestApiController {

	@Autowired
	private RestApiService restApiService;

	@GetMapping({""})
	public List<Map<String, Object>> select(ParameterMap parameterMap) {
		String pSearchArgv = "";
		String pPageNo = "";
		String pRowCount = "";
		
		if(parameterMap.containsKey("pSearchArgv")) {
			pSearchArgv = parameterMap.get("pSearchArgv").toString();
		}

		if(parameterMap.containsKey("pPageNo")) {
			pPageNo = parameterMap.get("pPageNo").toString();
		}
		
		if(parameterMap.containsKey("pPageNo")) {
			pRowCount = parameterMap.get("pRowCount").toString();
		}
		
		//pPageCount = "5";
		
		List<Map<String, Object>> list = restApiService.getRestApiList(pSearchArgv, pPageNo, pRowCount);
		return list;
	}
	
	@PostMapping("")
	public Object insertService(ParameterMap parameterMap) {
		int insCnt = restApiService.insertRestApi(parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("")
	public Object updateService(ParameterMap parameterMap) {
		
		int insCnt = restApiService.updateRestApi(parameterMap.getMap());
		return insCnt;
	}
	
	@DeleteMapping("/{apiSeq}")
	public Object deleteService(@PathVariable(value="apiSeq") String apiSeq, ParameterMap parameterMap) {
//		if(apiSeq.isEmpty()) {
//			/* serviceId is not null*/
//		}
		int delCnt = restApiService.deleteRestApi(apiSeq);
		return delCnt;
	}
}
