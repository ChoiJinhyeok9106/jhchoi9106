/**
 * 
 */
package kr.co.codefarm.svcm.additional.banner.controller;

import kr.co.codefarm.svcm.additional.banner.service.BannerApiService;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/api/additional/banner")
public class BannerApiController {
	
	@Autowired
	private BannerApiService bannerApiService;
	/**************************************************************
	 * 배너 개시 관련 부분
	 **************************************************************/
	@GetMapping("/post")
	public List<Map<String, Object>> getBannerPostList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = bannerApiService.getBannerPostList(parameterMap.getMap());
		return list;
	}
}
