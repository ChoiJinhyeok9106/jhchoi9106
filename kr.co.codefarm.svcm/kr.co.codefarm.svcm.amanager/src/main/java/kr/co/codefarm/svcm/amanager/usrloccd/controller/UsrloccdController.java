/**
 * 
 */
package kr.co.codefarm.svcm.amanager.usrloccd.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;

import org.springframework.beans.factory.annotation.Autowired;
import kr.co.codefarm.svcm.amanager.usrloccd.service.UsrloccdService;

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
@RequestMapping(value = "/amanager/usrloccd")
public class UsrloccdController {

	@Autowired
	private UsrloccdService UsrloccdService;
	
	// 사용위치관리 건물, 층 리스트 조회
	@GetMapping("/bldg-list")
	public List<Map<String, Object>> getBldgList(ParameterMap parameterMap) {
		return UsrloccdService.getBldgList(parameterMap.getMap());
	}
	
	// 사용위치관리 위치 리스트 조회
	@GetMapping("/loc-list")
	public List<Map<String, Object>> getLocList(ParameterMap parameterMap) {
		return UsrloccdService.getLocList(parameterMap.getMap());
	}
	
	// 사용위치관리 건물 입력
	@PostMapping("/insertBldg")
	public Object insertBldg(ParameterMap parameterMap) {
		int insCnt = UsrloccdService.insertBldg(parameterMap.getMap());
		return insCnt;
	}
	
	// 사용위치관리 건물 수정
	@PutMapping("/updateBldg")
	public Object updateBldg(ParameterMap parameterMap) {
		int updCnt = UsrloccdService.updateBldg(parameterMap.getMap());
		return updCnt;
	}	
	
	// 사용위치관리 층 입력
	@PostMapping("/insertStair")
	public Object insertStair(ParameterMap parameterMap) {
		int insCnt = UsrloccdService.insertStair(parameterMap.getMap());
		return insCnt;
	}
	
	// 사용위치관리 층 수정
	@PutMapping("/updateStair")
	public Object updateStair(ParameterMap parameterMap) {
		int updCnt = UsrloccdService.updateStair(parameterMap.getMap());
		return updCnt;
	}
	
	// 건물, 층 중복 체크
	@PostMapping("/checkCodeList")
	public List<Map<String, Object>> checkCodeList(ParameterMap parameterMap) {
		return UsrloccdService.checkCodeList(parameterMap.getMap());
	}
	
	// 사용위치관리 위치 입력
	@PostMapping("/insertLoc")
	public Object insertLoc(ParameterMap parameterMap) {
		int insCnt = UsrloccdService.insertLoc(parameterMap.getMap());
		return insCnt;
	}
	
	// 사용위치관리 위치 수정
	@PutMapping("/updateLoc")
	public Object updateLoc(ParameterMap parameterMap) {
		int updCnt = UsrloccdService.updateLoc(parameterMap.getMap());
		return updCnt;
	}	
	
}
