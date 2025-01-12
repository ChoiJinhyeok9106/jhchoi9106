/**
 * 
 */
package kr.co.codefarm.svcm.amanager.acqmgmt.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;

import org.springframework.beans.factory.annotation.Autowired;
import kr.co.codefarm.svcm.amanager.acqmgmt.service.AcqmgmtService;

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
@RequestMapping(value = "/amanager/acqmgmt")
public class AcqmgmtController {

	@Autowired
	private AcqmgmtService AcqmgmtService;
	
	
	// 시스템 관리 > 로그 관리 > 로그 목록 조회 
	@GetMapping("/acq-list")
	public List<Map<String, Object>> getAsstAcqMgmtList(ParameterMap parameterMap) {
		return AcqmgmtService.getAsstAcqMgmtList(parameterMap.getMap());
	}
	
	//입력
	@PostMapping("/insertAcq")
	public Object insertService(ParameterMap parameterMap) {
		int insCnt = AcqmgmtService.insertAsstAcq(parameterMap.getMap());
		return insCnt;
	}
	
	//수정
	@PutMapping("/updateAcq")
	public Object updateService(ParameterMap parameterMap) {
		int insCnt = AcqmgmtService.updateAsstAcq(parameterMap.getMap());
		return insCnt;
	}

	@DeleteMapping("/{assetAcqNo}")
	public Object deleteService(@PathVariable(value="assetAcqNo") String assetAcqNo, ParameterMap parameterMap) {
		int delCnt = AcqmgmtService.deleteAsstAcq(assetAcqNo);
		return delCnt;
	}
	
	//자산반영
	@PostMapping("/insertDtl")
	public Object insertDtlService(ParameterMap parameterMap) {
		int insCnt = AcqmgmtService.assetDtlInsert(parameterMap.getMap());
		return insCnt;
	}
	
	//자산반영여부 업데이트
	@PutMapping("/{assetAcqNo}")
	public Object updateRflcAtService(@PathVariable(value="assetAcqNo") String assetAcqNo, ParameterMap parameterMap) {
		int insCnt = AcqmgmtService.assetAcqRflcAtUpdate(assetAcqNo);
		return insCnt;
	}
	
}
