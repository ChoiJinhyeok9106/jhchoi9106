/**
 * 
 */
package kr.co.codefarm.svcm.amanager.useratymgmt.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;

import org.springframework.beans.factory.annotation.Autowired;
import kr.co.codefarm.svcm.amanager.useratymgmt.service.UseratymgmtService;

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
@RequestMapping(value = "/amanager/useratymgmt")
public class UseratymgmtController {

	@Autowired
	private UseratymgmtService UseratymgmtService;
	
	// 사용자관리 리스트 조회
	@GetMapping("/user-list")
	public List<Map<String, Object>> getUsrAtyMgmtList(ParameterMap parameterMap) {
		return UseratymgmtService.getUsrAtyMgmtList(parameterMap.getMap());
	}
	
	// 권한 리스트 조회(select)
	@GetMapping("/AuthorCode-list")
	public List<Map<String, Object>> getAuthorCodeList(ParameterMap parameterMap) {
		return UseratymgmtService.getAuthorCodeList(parameterMap.getMap());
	}
	
	// 사용자관리 입력
	@PostMapping("/insertUsrMgmt")
	public Object insertService(ParameterMap parameterMap) {
		int insCnt = UseratymgmtService.insertUsrMgmt(parameterMap.getMap());
		return insCnt;
	}
	
	// 사용자관리 수정
	@PutMapping("/updateUsrMgmt")
	public Object updateService(ParameterMap parameterMap) {
		int updCnt = UseratymgmtService.updateUsrMgmt(parameterMap.getMap());
		return updCnt;
	}
	
}
