/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.common.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.common.service.CommonService;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/system-manager/common")
public class CommonController {

	@Autowired
	private CommonService commonService;

	@GetMapping({""})
	public List<Map<String, Object>> getCommonList(ParameterMap parameterMap) {
		String pServiceType = parameterMap.get("pServiceType").toString();
		String pSearchType = parameterMap.get("pSearchType").toString();
		String pSearchArgv = parameterMap.get("pSearchArgv").toString();
		String pComCodeUpCd = parameterMap.get("pComCodeUpCd").toString();
		String pPageNo = parameterMap.get("pPageNo").toString();
		String pRowCount = parameterMap.get("pRowCount").toString();
		//pPageCount = "5";
		
		List<Map<String, Object>> list = commonService.getCommonList(pServiceType, pSearchType, pSearchArgv, pComCodeUpCd, pPageNo, pRowCount);
		return list;
	}
	
	@GetMapping({"/service"})
	public List<Map<String, Object>> getServiceList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = commonService.getServiceList();
		return list;
	}
	
	@GetMapping("/test")
	public void test(ParameterMap parameterMap) {
		try {
			throw new IOException("test");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/cd/{cd}")
	public Object getCustomCdList(@PathVariable(value="cd") String cd, ParameterMap parameterMap) {
//		if(cd.isEmpty()) {
//			/* serviceId is not null*/
//		}
		
		Object common = commonService.getCustomCd(cd);
		return common;
	}
	
	/*
	@GetMapping("/{serviceCd}")
	public Object getService(@PathVariable(value="serviceCd") String serviceCd, ParameterMap parameterMap) {
		if(serviceCd.isEmpty()) {
		
		}
		
		Object service = commonService.getCommon(serviceCd);
		return service;
	}
	*/
	
	@PostMapping("")
	public Object insertCommon(String commonCd, ParameterMap parameterMap) {
		int insCnt = commonService.insertCommon(parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("")
	public Object updateCommon(String commonCd, ParameterMap parameterMap) {
		int insCnt = commonService.updateCommon(parameterMap.getMap());
		return insCnt;
	}
	
	@PatchMapping("")
	public Object updateCustomCd(String commonCd, ParameterMap parameterMap) {
		int insCnt = commonService.updateCustomCd(parameterMap.getMap());
		return insCnt;
	}
	
	@DeleteMapping("")
	public Object deleteCommon(String commonCd, ParameterMap parameterMap) {
		String pUpCd = (String) parameterMap.get("pUpCd");
		String pCd = (String) parameterMap.get("pCd");
		
		int delCnt = commonService.deleteCommon(pUpCd, pCd);
		return delCnt;
	}
}
