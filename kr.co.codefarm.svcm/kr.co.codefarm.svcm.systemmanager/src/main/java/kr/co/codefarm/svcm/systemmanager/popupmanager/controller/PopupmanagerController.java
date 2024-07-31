/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.popupmanager.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.popupmanager.service.PopupmanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author syyoon
 *
 */
@RestController
@RequestMapping(value = "/system-manager/popupmanager")
public class PopupmanagerController {

	@Autowired
	private PopupmanagerService popupmanagerService;

	@GetMapping("")
	public List<Map<String, Object>> searchPopup(ParameterMap parameterMap) {
		List<Map<String, Object>> list = popupmanagerService.searchPopup(parameterMap.getMap());
		return list;
	}

	@PostMapping("")
	public Object popupInsert(ParameterMap parameterMap) {
		int insCnt = popupmanagerService.popupInsert(parameterMap.getMap());
		return insCnt;
	}

	@PutMapping("")
	public Object popupUpdate(ParameterMap parameterMap) {

		int insCnt = popupmanagerService.popupUpdate(parameterMap.getMap());
		return insCnt;
	}

	@PutMapping("/useYnToggle")
	public Object useYnToggle(ParameterMap parameterMap) {

		int insCnt = popupmanagerService.useYnToggle(parameterMap.getMap());
		return insCnt;
	}

	@DeleteMapping("")
	public Object popupDelete(ParameterMap parameterMap) {
		int delCnt = popupmanagerService.popupDelete(parameterMap.getMap());
		return delCnt;
	}

}
