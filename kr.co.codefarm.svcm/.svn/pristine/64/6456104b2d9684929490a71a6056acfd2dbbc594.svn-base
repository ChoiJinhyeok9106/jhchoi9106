/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.vacation.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.vacation.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author hrchoi
 *
 */
@RestController
@RequestMapping(value = "/system-manager/vacation")
public class VacationController {

	@Autowired
	private VacationService vacationService;

	@GetMapping("")
	public List<Map<String, Object>> getVacationList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = vacationService.getVacationList(parameterMap.getMap());
		return list;
	}
	@GetMapping("/getExistsYear")
	public List<Map<String, Object>> getExistsYear(ParameterMap parameterMap) {
		List<Map<String, Object>> list = vacationService.getExistsYear(parameterMap.getMap());
		return list;
	}
	@PutMapping("")
	public Object putVacationList(ParameterMap parameterMap) {
		int updCnt = vacationService.putVacationList(parameterMap.getMap());
		return updCnt;
	}
	/*휴가사용 반영*/
	@PutMapping("/usedDt")
	public Object updateUsedVacation(ParameterMap parameterMap) {
		int updCnt = vacationService.updateUsedVacation(parameterMap.getMap());
		return updCnt;
	}
	@PostMapping("")
	public Object insertLoadList(ParameterMap parameterMap) {
		int insCnt = 0;
		List list = (List)parameterMap.get("JSON_DATA_LIST");

		for(int i = 0; i < list.size(); i++) {
			Map paramMap = (Map<String, Object>) list.get(i);
			paramMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
			paramMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
			paramMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));

			insCnt += vacationService.insertVacacion(paramMap);
		}
		return insCnt;
	}

}
