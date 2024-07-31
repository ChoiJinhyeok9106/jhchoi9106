/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.defaultinfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.codefarm.svcm.commons.annotation.DefaultInfoRelay;
import kr.co.codefarm.svcm.commons.annotation.ManagedAPI;
import kr.co.codefarm.svcm.commons.api.dto.DefaultInfoDto;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.DefaultInfoMap;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.utils.ObjectUtil;
import kr.co.codefarm.svcm.systemmanager.defaultinfo.service.DefaultInfoService;

/**
 * @author PHS
 *
 */
@RestController
@ManagedAPI
@RequestMapping(value = "/system-manager/default-info")
public class DefaultInfoController {

	@Autowired
	private DefaultInfoService defaultInfoService;
	
	@GetMapping({""})
	public List<Map<String, Object>> getServiceList(ParameterMap parameterMap) {
		String pSearchType = parameterMap.get("pSearchType").toString();
		String pSearchArgv = parameterMap.get("pSearchArgv").toString();
		String pPageNo = parameterMap.get("pPageNo").toString();
		String pRowCount = parameterMap.get("pRowCnt").toString();
		/* 2020.08.20 pPageCount 쓰이지 않은것 같아 주석처리(작성자 : 최대건)*/
//		String pPageCount = parameterMap.get("pPageCount").toString();
//		pPageCount = "5";
		
		List<Map<String, Object>> list = defaultInfoService.getDefaultInfoList(pSearchType, pSearchArgv, pPageNo, pRowCount);
		return list;
	}
	
	@GetMapping("/representative")
	public Object getRepresentative(ParameterMap parameterMap) {
//		String pSiteSeq = (String) parameterMap.get("pSiteSeq");
//		Object representative = defaultInfoService.getRepresentative(pSiteSeq);
//		return representative;
		return DefaultInfoMap.getMap();
	}
	
	@SuppressWarnings("unchecked")
	@DefaultInfoRelay
	public DefaultInfoDto getDefaultInfo() {
		String pSiteSeq = "";
		Map<String, Object> map = (Map<String, Object>) defaultInfoService.getRepresentative(pSiteSeq);
		DefaultInfoDto defaultInfoDto = (DefaultInfoDto) ObjectUtil.convertMapToObject(map, new DefaultInfoDto());
		
		return defaultInfoDto;
	}
	
	@PostMapping("")
	public Object insertService(ParameterMap parameterMap) {
		int insCnt = defaultInfoService.insertDefaultInfo(parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("")
	public Object updateService(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		int insCnt = defaultInfoService.updateDefaultInfo(parameterMap.getMap());
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("BOARD_SEQ", "4");
		paramMap.put("TITLE", parameterMap.get("AGREE_ITEM1_NM"));
		paramMap.put("USER_ID", userDetailsImpl.getInternalId());
		paramMap.put("USER_NM", userDetailsImpl.getUserNm());
		paramMap.put("CONTENTS", parameterMap.get("AGREE_ITEM1_CONTENTS"));
		insCnt += defaultInfoService.insertHistory(paramMap);
		paramMap = new HashMap<>();
		paramMap.put("BOARD_SEQ", "5");
		paramMap.put("TITLE", parameterMap.get("AGREE_ITEM2_NM"));
		paramMap.put("USER_ID", userDetailsImpl.getInternalId());
		paramMap.put("USER_NM", userDetailsImpl.getUserNm());
		paramMap.put("CONTENTS", parameterMap.get("AGREE_ITEM2_CONTENTS"));
		insCnt += defaultInfoService.insertHistory(paramMap);
		return insCnt;
	}
	
	@DeleteMapping("/{siteSeq}")
	public Object deleteService(@PathVariable(value="siteSeq") String siteSeq, ParameterMap parameterMap) {
		
//		if(siteSeq.isEmpty()) {
//			/* serviceId is not null*/
//		}
		int delCnt = defaultInfoService.deleteDefaultInfo(siteSeq);
		return delCnt;
	}
	
}
