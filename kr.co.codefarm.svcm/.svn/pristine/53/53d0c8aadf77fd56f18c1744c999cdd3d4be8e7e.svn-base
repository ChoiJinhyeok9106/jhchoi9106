/**
 * 
 */
package kr.co.codefarm.svcm.amanager.codemgmt.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;

import org.springframework.beans.factory.annotation.Autowired;
import kr.co.codefarm.svcm.amanager.codemgmt.service.CodemgmtService;

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
@RequestMapping(value = "/amanager/codemgmt")
public class CodemgmtController {

	@Autowired
	private CodemgmtService CodemgmtService;
	
	// 공통코드 리스트 조회
	@GetMapping("/code-list")
	public List<Map<String, Object>> getCodeMgmtList(ParameterMap parameterMap) {
		return CodemgmtService.getCodeMgmtList(parameterMap.getMap());
	}
	
	// 공통코드 리스트 상세 조회
	@GetMapping("/codedetl-list")
	public List<Map<String, Object>> getCodeMgmtListDetail(ParameterMap parameterMap) {
		return CodemgmtService.getCodeMgmtListDetail(parameterMap.getMap());
	}
	
	// 정렬순서 중복 체크
	@PostMapping("/checkOrderSeq")
	public List<Map<String, Object>> checkOrderSeq(ParameterMap parameterMap) {
		return CodemgmtService.checkOrderSeq(parameterMap.getMap());
	}
	
	// 상세코드 중복 체크
	@PostMapping("/checkCodeList")
	public List<Map<String, Object>> checkCodeList(ParameterMap parameterMap) {
		return CodemgmtService.checkCodeList(parameterMap.getMap());
	}	
	
	// 공통상세코드 입력
	@PostMapping("/insertCode")
	public Object insertService(ParameterMap parameterMap) {
		int insCnt = CodemgmtService.insertCodeMgmtDetail(parameterMap.getMap());
		return insCnt;
	}
	
	// 공통상세코드 수정
	@PutMapping("/updateCode")
	public Object updateService(ParameterMap parameterMap) {
		int updCnt = CodemgmtService.updateCodeMgmtDetail(parameterMap.getMap());
		return updCnt;
	}	
	
}
