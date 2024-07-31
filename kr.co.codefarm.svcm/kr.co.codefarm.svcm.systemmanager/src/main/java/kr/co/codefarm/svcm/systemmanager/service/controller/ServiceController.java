package kr.co.codefarm.svcm.systemmanager.service.controller;

import kr.co.codefarm.svcm.commons.annotation.ManagedAPI;
import kr.co.codefarm.svcm.commons.annotation.ServicesRelay;
import kr.co.codefarm.svcm.commons.api.dto.ServiceDto;
import kr.co.codefarm.svcm.commons.controller.BaseController;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.utils.ObjectUtil;
import kr.co.codefarm.svcm.systemmanager.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@ManagedAPI
@RestController
@RequestMapping(value = "/system-manager/service")
public class ServiceController extends BaseController {
	
	@Autowired
	private ServiceService serviceService;
	
	@ServicesRelay
	public List<ServiceDto> addSelectServiceListHandler() {		
		List<ServiceDto> retList = new ArrayList<>();
		List<Map<String, Object>> list = serviceService.getServiceListForSystem(null, null, null);
		
		for (Map<String, Object> map : list) {
			ServiceDto serviceDto = (ServiceDto) ObjectUtil.convertMapToObject(map, new ServiceDto());
			retList.add(serviceDto);
		}
		
		return retList;
	}
	
	@GetMapping({"/initList"})
	public List<ServiceDto> addSelectServiceHandler(ParameterMap parameterMap) {
		String pServiceCd = (String) parameterMap.get("pServiceCd");
		String pServiceNm = (String) parameterMap.get("pServiceNm");
		String pCommcodeUseYn = (String) parameterMap.get("pCommcodeUseYn");
		
		List<ServiceDto> retList = new ArrayList<>();
		List<Map<String, Object>> list = serviceService.getServiceListForSystem(pServiceCd, pServiceNm, pCommcodeUseYn);
		
		for (Map<String, Object> map : list) {
			ServiceDto serviceDto = (ServiceDto) ObjectUtil.convertMapToObject(map, new ServiceDto());
			retList.add(serviceDto);
		}
		
		return retList;
	}

	@GetMapping("/codeUseList")
	public List<Map<String, Object>> getCommCodeUseList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = serviceService.getCommCodeUseList(parameterMap.getMap());
		return list;
	}
	
	@GetMapping({""})
	public List<Map<String, Object>> getServiceList(ParameterMap parameterMap) {
		
		String pSearchType = parameterMap.get("pSearchType").toString();
		String pSearchArgv = parameterMap.get("pSearchArgv").toString();
		String pPageNo = parameterMap.get("pPageNo").toString();
		String pRowCount = parameterMap.get("pRowCount").toString();
		
		List<Map<String, Object>> list = serviceService.getServiceList(pSearchType, pSearchArgv, pPageNo, pRowCount);
		return list;
	}
	
	@GetMapping("/{serviceCd}")
	public Object getService(@PathVariable(value="serviceCd") String serviceCd, ParameterMap parameterMap) {
//		if(serviceCd.isEmpty()) {
//			/* serviceId is not null*/
//		}
		
		Object service = serviceService.getService(serviceCd);
		return service;
	}
	
	@PostMapping("")
	public Object insertService(ParameterMap parameterMap) {
		int insCnt = serviceService.insertService(parameterMap.getMap());
		return insCnt;
	}
	
	@PutMapping("")
	public Object updateService(ParameterMap parameterMap) {
		int insCnt = serviceService.updateService(parameterMap.getMap());
		return insCnt;
	}
	
	@DeleteMapping("/{serviceCd}")
	public Object deleteService(@PathVariable(value="serviceCd") String serviceCd, ParameterMap parameterMap) {
//		if(serviceCd.isEmpty()) {
//			/* serviceId is not null*/
//		}
		int delCnt = serviceService.deleteService(serviceCd);
		return delCnt;
	}
	
}
