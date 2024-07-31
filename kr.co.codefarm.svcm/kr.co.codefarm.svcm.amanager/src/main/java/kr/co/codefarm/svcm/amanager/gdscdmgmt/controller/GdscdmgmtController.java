/**
 * 
 */
package kr.co.codefarm.svcm.amanager.gdscdmgmt.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;

import org.springframework.beans.factory.annotation.Autowired;
import kr.co.codefarm.svcm.amanager.gdscdmgmt.service.GdscdmgmtService;

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
@RequestMapping(value = "/amanager/gdscdmgmt")
public class GdscdmgmtController {

	@Autowired
	private GdscdmgmtService GdscdmgmtService;
	
	// 물품분류 조회
	@GetMapping("/astcl-list")
	public List<Map<String, Object>> getAssetClList(ParameterMap parameterMap) {
		return GdscdmgmtService.getAssetClList(parameterMap.getMap());
	}
	
	// 물품규격 조회
	@GetMapping("/aststnd-list")
	public List<Map<String, Object>> getAssetStndList(ParameterMap parameterMap) {
		return GdscdmgmtService.getAssetStndList(parameterMap.getMap());
	}

	// 물품분류 입력
	@PostMapping("/insertAssetClCd")
	public Object insertAssetClCd(ParameterMap parameterMap) {
		int insCnt = GdscdmgmtService.insertAssetClCd(parameterMap.getMap());
		return insCnt;
	}
	
	// 물품분류 수정
	@PutMapping("/updateAssetClCd")
	public Object updateAssetClCd(ParameterMap parameterMap) {
		int insCnt = GdscdmgmtService.updateAssetClCd(parameterMap.getMap());
		return insCnt;
	}
	
	// 물품분류 삭제시 사용여부체크
	@PostMapping("/checkUseAssetClCd")
	public List<Map<String, Object>> checkUseAssetClCd(ParameterMap parameterMap) {
		return GdscdmgmtService.checkUseAssetClCd(parameterMap.getMap());
	}
	
	// 물품분류 삭제
	@DeleteMapping("/{assetClNo}")
	public Object deleteAssetClCd(@PathVariable(value="assetClNo") String assetClNo, ParameterMap parameterMap) {
		int delCnt = GdscdmgmtService.deleteAssetClCd(assetClNo);
		return delCnt;
	}
	
	// 물품규격번호 중복체크
	@PostMapping("/checkAssetStndList")
	public List<Map<String, Object>> checkAssetStndList(ParameterMap parameterMap) {
		return GdscdmgmtService.checkAssetStndList(parameterMap.getMap());
	}
	
	// 물품규격 입력
	@PostMapping("/insertAssetStndCd")
	public Object insertAssetStndCd(ParameterMap parameterMap) {
		int insCnt = GdscdmgmtService.insertAssetStndCd(parameterMap.getMap());
		return insCnt;
	}
	
	// 물품규격 수정
	@PutMapping("/updateAssetStndCd")
	public Object updateAssetStndCd(ParameterMap parameterMap) {
		int insCnt = GdscdmgmtService.updateAssetStndCd(parameterMap.getMap());
		return insCnt;
	}
	
	// 물품분류 삭제시 사용여부체크
	@PostMapping("/checkUseAssetStndCd")
	public List<Map<String, Object>> checkUseAssetStndCd(ParameterMap parameterMap) {
		return GdscdmgmtService.checkUseAssetStndCd(parameterMap.getMap());
	}
	
	// 물품분류 수정
	@DeleteMapping("/deleteAssetStndCd")
	public Object deleteAssetStndCd(ParameterMap parameterMap) {
		int delCnt = GdscdmgmtService.deleteAssetStndCd(parameterMap.getMap());
		return delCnt;
	}
}
