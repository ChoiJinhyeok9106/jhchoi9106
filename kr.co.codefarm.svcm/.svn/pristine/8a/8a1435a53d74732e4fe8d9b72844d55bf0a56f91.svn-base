package kr.co.codefarm.svcm.systemmanager.subscribe.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.subscribe.service.SubscribeService;

@RestController
@RequestMapping("/system-manager/subscribe")
public class SubscribeController {

	@Autowired
	private SubscribeService subscribeService;
	
	@GetMapping("")
	public List<Map<String, Object>> getSubscribeList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap){
		parameterMap.put("CORP_ID", userDetailsImpl.getCorpId());
		return subscribeService.getSubscribeList(parameterMap.getMap());
	}
	
	@PostMapping("")
	public Object subscription(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap){
		parameterMap.put("CORP_ID", userDetailsImpl.getCorpId());
		return subscribeService.subscription(parameterMap.getMap());
	}
	
	@DeleteMapping("")
	public Object cancelSubscription(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap){
		parameterMap.put("CORP_ID", userDetailsImpl.getCorpId());
		return subscribeService.cancelSubscription(parameterMap.getMap());
	}

	@GetMapping("/subscription")
	public List<Map<String, Object>> getSubscriptionList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap){
		return subscribeService.getSubscriptionList(parameterMap.getMap());
	}
	
	@PostMapping("/apply")
	public Object acceptService(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap){
		return subscribeService.acceptService(parameterMap.getMap());
	}
	
	@DeleteMapping("/apply")
	public Object rejectService(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap){
		return subscribeService.rejectService(parameterMap.getMap());
	}

	@GetMapping("/my")
	public List<Map<String, Object>> getMySubscribeList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap){
		parameterMap.put("CORP_ID", userDetailsImpl.getCorpId());
		return subscribeService.getMySubscribeList(parameterMap.getMap());
	}
}
