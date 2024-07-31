/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.app.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/system-manager/app")
public class AppController {

	@Autowired
	private AppService appService;

	/*get apps*/
	@GetMapping({""})
	public List<Map<String, Object>> getAppList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = appService.getAppList(parameterMap.getMap());
		return list;
	}
	
	/*get app*/
	@GetMapping("/{appSeq}")
	public Object getApp(@PathVariable(value="appSeq") String appSeq, ParameterMap parameterMap) {
//		if(appSeq.isEmpty()) {
//			/* appSeq is not null*/
//		}
		
		Object app = appService.getApp(appSeq);
		return app;
	}
	
	/*insert app*/
	@PostMapping("")
	public int insertApp(ParameterMap parameterMap) {
		int insertCnt = appService.insertApp(parameterMap.getMap());
		return insertCnt;
	}
	
	/*update app*/
	@PutMapping("")
	public Object updateApp(ParameterMap parameterMap) {
		int insCnt = appService.updateApp(parameterMap.getMap());
		return insCnt;
	}
	
	/*delete app*/
	@DeleteMapping("/{appId}")
	public Object deleteApp(@PathVariable(value="appId") String appId, ParameterMap parameterMap) {
//		if(appId.isEmpty()) {
//			/* appId is not null*/
//		}
		int delCnt = appService.deleteApp(appId);
		return delCnt;
	}

}
