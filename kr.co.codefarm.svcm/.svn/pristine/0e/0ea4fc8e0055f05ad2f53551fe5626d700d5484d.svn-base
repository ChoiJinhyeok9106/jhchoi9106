/**
 * 
 */
package kr.co.codefarm.svcm.additional.banner.controller;

import kr.co.codefarm.svcm.additional.banner.service.BannerService;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/additional/banner")
public class BannerController {
	
	@Autowired
	private BannerService bannerService;

	/**************************************************************
	 * 배너 관련 부분
	 **************************************************************/
	@GetMapping({""})
	public List<Map<String, Object>> getBannerList(ParameterMap parameterMap) {
		String pSearchType = parameterMap.get("pSearchType").toString();
		String pSearchArgv = parameterMap.get("pSearchArgv").toString();
		String pPageNo = parameterMap.get("pPageNo").toString();
		String pRowCount = parameterMap.get("pRowCnt").toString();
		String pMenuGb = parameterMap.get("pMenuGb").toString();

		List<Map<String, Object>> list = bannerService.getBannerList(pSearchType, pSearchArgv, pPageNo, pRowCount, pMenuGb);
		return list;
	}
	
	@GetMapping("/{bannerSeq}")
	public Object getBanner(@PathVariable(value="bannerSeq") String bannerSeq, ParameterMap parameterMap) {
		if(bannerSeq.isEmpty()) {
			/* serviceId is not null*/
		}
		
		Object banner = bannerService.getBanner(bannerSeq);
		return banner;
	}
	
	@PostMapping("")
	public Object insertBanner(ParameterMap parameterMap) {
		int insCnt = bannerService.insertBanner(parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("")
	public Object updateBanner(ParameterMap parameterMap) {
		int updCnt = bannerService.updateBanner(parameterMap.getMap());
		return updCnt;
	}
	
	@DeleteMapping("/{bannerSeq}")
	public Object deleteBanner(@PathVariable(value="bannerSeq") String bannerSeq, ParameterMap parameterMap) {
		if(bannerSeq.isEmpty()) {
			/* serviceId is not null*/
		}
		
		int delCnt = bannerService.deleteBanner(bannerSeq);
		return delCnt;
	}
	
	/**************************************************************
	 * 배너 그룹 관련 부분
	 **************************************************************/
	@GetMapping({"/group"})
	public List<Map<String, Object>> getBannerGroupList(ParameterMap parameterMap) {
		String pSearchType = parameterMap.get("pSearchType").toString();
		String pSearchArgv = parameterMap.get("pSearchArgv").toString();
		String pPageNo = "";
		String pRowCount= "";
		
		if(parameterMap.containsKey("pPageNo")) {
			pPageNo = parameterMap.get("pPageNo").toString();
		}
		if(parameterMap.containsKey("pRowCnt")) {
			pRowCount = parameterMap.get("pRowCnt").toString();
		}
		
		List<Map<String, Object>> list = bannerService.getBannerGroupList(pSearchType, pSearchArgv, pPageNo, pRowCount);
		return list;
	}
	
	@GetMapping("/group/{bannerGroupSeq}")
	public Object getBannerGroup(@PathVariable(value="bannerGroupSeq") String bannerGroupSeq, ParameterMap parameterMap) {
		if(bannerGroupSeq.isEmpty()) {
			/* serviceId is not null*/
		}
		
		Object service = bannerService.getBannerGroup(bannerGroupSeq);
		return service;
	}
	
	@PostMapping("/group")
	public Object insertBannerGroup(ParameterMap parameterMap) {
		int insCnt = bannerService.insertBannerGroup(parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("/group")
	public Object updateBannerGroup(ParameterMap parameterMap) {
		int updCnt = bannerService.updateBannerGroup(parameterMap.getMap());
		return updCnt;
	}
	
	@DeleteMapping("/group/{bannerGroupSeq}")
	public Object deleteBannerGroup(@PathVariable(value="bannerGroupSeq") String bannerGroupSeq, ParameterMap parameterMap) {
		if(bannerGroupSeq.isEmpty()) {
			/* serviceId is not null*/
		}
		
		int delCnt = bannerService.deleteBannerGroup(bannerGroupSeq);
		return delCnt;
	}

	/**************************************************************
	 * 배너 개시 관련 부분
	 **************************************************************/
	@GetMapping({"/total-post"})
	public List<Map<String, Object>> getBannerTotalPostList(ParameterMap parameterMap) {
		String pBannerGroupSeq = (String) parameterMap.get("pBannerGroupSeq");
		List<Map<String, Object>> list = bannerService.getBannerTotalPostList(pBannerGroupSeq);
		return list;
	}
	
	@PostMapping("/post")
	public Object insertBannerPost(ParameterMap parameterMap) {
		int insCnt = 0;
		
		List bannerList = (List) parameterMap.get("JSON_DATA_LIST");
		
		for(int i = 0; i < bannerList.size(); i++) {
			Map paramMap = (Map<String, Object>) bannerList.get(i);
			paramMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
			paramMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
			paramMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
			
			insCnt = bannerService.insertBannerPost(paramMap);
		}
		
		return insCnt;
	}
	
	@PutMapping("/post-ord")
	public Object updateOrdBannerPost(ParameterMap parameterMap) {
		int updCnt = 0;
		
		List bannerList = (List) parameterMap.get("JSON_DATA_LIST");
		
		for(int i = 0; i < bannerList.size(); i++) {
			Map paramMap = (Map<String, Object>) bannerList.get(i);
			paramMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
			paramMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
			paramMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
			
			updCnt = bannerService.updateOrdBannerPost(paramMap);
		}
		
		return updCnt;
	}
	
	@DeleteMapping("/post")
	public Object deleteOrdBannerPost(ParameterMap parameterMap) {
		String pBannerGroupSeq = (String) parameterMap.get("pBannerGroupSeq");
		String pBannerSeq = (String) parameterMap.get("pBannerSeq");
		String pPostSeq = (String) parameterMap.get("pPostSeq");
		
		int delCnt = bannerService.deleteOrdBannerPost(pBannerGroupSeq, pBannerSeq, pPostSeq);
		return delCnt;
	}
	
	@PutMapping("/post")
	public Object updateBannerPost(ParameterMap parameterMap) {
		int updCnt = bannerService.updateBannerPost(parameterMap.getMap());
		return updCnt;
	}
	
}
