/** 
 * 
 */
package kr.co.codefarm.svcm.systemmanager.program.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.program.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/system-manager/program")
public class ProgramController {

	@Autowired
	private ProgramService programService;

	//Program Controller
	@GetMapping({""})
	public List<Map<String, Object>> getProgramList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = programService.getProgramList(parameterMap.getMap());
		return list;
	}
	
	@PostMapping("")
	public Object insertProgram(ParameterMap parameterMap) {
		Map<String, Object> insObj = programService.insertProgram(parameterMap.getMap());
		return insObj;
	}
	
	
	@PutMapping("")
	public Object updateProgram(String programSeq, ParameterMap parameterMap) {
		int insCnt = programService.updateProgram(parameterMap.getMap());
		return insCnt;
	}
	
	
	@DeleteMapping("")
	public Object deleteProgram(ParameterMap parameterMap) {
		int delCnt = programService.deleteProgram(parameterMap.getMap());
		return delCnt;
	}
}
