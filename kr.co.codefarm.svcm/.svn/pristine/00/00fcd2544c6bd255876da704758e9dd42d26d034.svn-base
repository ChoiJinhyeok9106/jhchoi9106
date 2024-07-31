package kr.co.codefarm.svcm.commons.controller.comm;

import kr.co.codefarm.svcm.commons.api.manager.ManagedApiCollector;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
@RestController
@RequestMapping("/daemon")
public class SvcmCollectorDaemonController {
	
	@Autowired
	private ManagedApiCollector managedApiCollector;

	@GetMapping(value = "/alarm")
	public void setAlarmMap(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) throws IOException {
		managedApiCollector.setAlarmMap();
	}

	@GetMapping(value = "/auth")
	public void setAuthMap(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) throws IOException {
		managedApiCollector.setAuthMap();
	}

	@GetMapping(value = "/defaultinfo")
	public void setDefaultInfoMap(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) throws IOException {
		managedApiCollector.setDefaultInfoMap();
	}

	@GetMapping(value = "/service")
	public void setServiceMap(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) throws IOException {
		managedApiCollector.setServiceMap();
	}

//	@GetMapping(value = "/apibean")
//	public void setManagedApiBeanMap(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) throws IOException {
//		managedApiCollector.setManagedApiBeanMap();
//	}
	
}
