/**
 * 
 */
package kr.co.codefarm.svcm.amanager.deptmgmt.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;

import org.springframework.beans.factory.annotation.Autowired;
import kr.co.codefarm.svcm.amanager.deptmgmt.service.DeptmgmtService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 
 *
 */
@RestController
@RequestMapping(value = "/amanager/deptmgmt")
public class DeptmgmtController {

	@Autowired
	private DeptmgmtService DeptmgmtService;
	
	// 시스템 관리 > 로그 관리 > 로그 목록 조회 
	@GetMapping("/dept-list")
	public List<Map<String, Object>> getDeptMgmtList(ParameterMap parameterMap) {
		return DeptmgmtService.getDeptMgmtList(parameterMap.getMap());
	}
	
	//입력
	@PostMapping("/insertDept")
	public Object insertService(ParameterMap parameterMap) {
		int insCnt = DeptmgmtService.insertDept(parameterMap.getMap());
		return insCnt;
	}
	
	//수정
	@PutMapping("/updateDept")
	public Object updateService(ParameterMap parameterMap) {
		int insCnt = DeptmgmtService.updateDept(parameterMap.getMap());
		return insCnt;
	}
	
	//중복체크
	@PostMapping("/checkUpdateDept")
	public List<Map<String, Object>> checkDeptMgmt(ParameterMap parameterMap) {
		return DeptmgmtService.checkDeptMgmt(parameterMap.getMap());
	}
	
	//미사용처리
	@DeleteMapping("/{deptCd}")
	public Object useNotService(@PathVariable(value="deptCd") String deptCd, ParameterMap parameterMap) {
		int delCnt = DeptmgmtService.deptUseNot(deptCd);
		return delCnt;
	}
	
	
	
}
