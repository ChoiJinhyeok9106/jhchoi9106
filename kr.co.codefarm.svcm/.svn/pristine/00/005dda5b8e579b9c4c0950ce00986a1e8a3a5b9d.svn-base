/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.linkplus.controller;

import kr.co.codefarm.svcm.commons.annotation.ManagedAPI;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.linkplus.service.LinkplusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@ManagedAPI
@RequestMapping(value = "/system-manager/link-plus")
public class LinkplusController {

	@Autowired
	private LinkplusService linkplusService;
	
	@GetMapping({""})
	public List<Map<String, Object>> getLinkplusList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = linkplusService.getLinkplusList(parameterMap.getMap());
		return list;
	}
	
	@PostMapping("")
	public Object insertLinkplus(ParameterMap parameterMap) {
		int insCnt = linkplusService.insertLinkplus(parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("")
	public Object updateLinkplus(ParameterMap parameterMap) {
		int insCnt = linkplusService.updateLinkplus(parameterMap.getMap());
		return insCnt;
	}
	
	@DeleteMapping("")
	public Object deleteLinkplus(ParameterMap parameterMap) {
		int delCnt = linkplusService.deleteLinkplus(parameterMap.getMap());
		return delCnt;
	}
	
}
