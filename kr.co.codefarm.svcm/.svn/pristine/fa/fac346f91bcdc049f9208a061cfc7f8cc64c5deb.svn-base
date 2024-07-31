/**
 * 
 */
package kr.co.codefarm.svcm.commons.api.controller;

import kr.co.codefarm.svcm.commons.api.dto.CodeDto;
import kr.co.codefarm.svcm.commons.api.dto.MenuDto;
import kr.co.codefarm.svcm.commons.controller.BaseController;
import kr.co.codefarm.svcm.commons.map.ManagedApiBeanMap;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.utils.ObjectUtil;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Dki_s
 *
 */
@RestController
public class CommonAPI extends BaseController {
	
//	@GetMapping(value = "/comm/services")
//	public List<ServiceDto> selAllServices() {
//		List<ServiceDto> retList = new ArrayList<>();
//		
//		for (String key : ManagedApiBeanMap.keySet()) {
//			List<ServiceDto> item = ManagedApiBeanMap.get(key).addSelectServiceHandler();
//			if (item != null) {
//				retList.addAll(item);
//			}
//		}
//
//		return retList;
//	}
	
	@GetMapping(value = "/comm/codes")
	public List<CodeDto> selAllCodes() {
		List<CodeDto> retList = 
				new ArrayList<>();
		
		for (String key : ManagedApiBeanMap.keySet()) {
			List<CodeDto> item = ManagedApiBeanMap.get(key).addSelectCodesHandler(null);
			if (item != null) {
				retList.addAll(item);
			}
		}

		return retList;
	}
	
	@GetMapping(value = "/comm/{service}/codes")
	public List<CodeDto> selCodes(@PathVariable(value = "service") String service, ParameterMap parameterMap) {
		return ManagedApiBeanMap.get(service).addSelectCodesHandler(parameterMap.getMap());
	}
	
	@GetMapping(value = "/comm/{service}/code")
	public CodeDto selCode(@PathVariable(value = "service") String service, ParameterMap parameterMap) {
		return ManagedApiBeanMap.get(service).addSelectCodeHandler(parameterMap.getMap());
	}
	
	@PostMapping(value = "/comm/{service}/code")
	public Object insCode(@PathVariable(value = "service") String service, ParameterMap parameterMap) {
		CodeDto codeDto = (CodeDto)ObjectUtil.convertMapToObject(parameterMap.getMap(), new CodeDto());
		return ManagedApiBeanMap.get(service).addInsertCodeHandler(codeDto);
	}
	
	@PutMapping(value = "/comm/{service}/code")
	public Object updCode(@PathVariable(value = "service") String service, ParameterMap parameterMap) {
		CodeDto codeDto = (CodeDto)ObjectUtil.convertMapToObject(parameterMap.getMap(), new CodeDto());
		return ManagedApiBeanMap.get(service).addUpdateCodeHandler(codeDto);
	}
	
	@DeleteMapping(value = "/comm/{service}/code")
	public Object delCode(@PathVariable(value = "service") String service, ParameterMap parameterMap) {
		CodeDto codeDto = (CodeDto)ObjectUtil.convertMapToObject(parameterMap.getMap(), new CodeDto());
		return ManagedApiBeanMap.get(service).addDeleteCodeHandler(codeDto);
	}
	
	@GetMapping(value = "/comm/menus")
	public List<MenuDto> selAllMenus() {
		List<MenuDto> retList = new ArrayList<>();
		
		for (String key : ManagedApiBeanMap.keySet()) {
			List<MenuDto> item = ManagedApiBeanMap.get(key).addSelectMenuHandler(null);
			if (item != null) {
				retList.addAll(item);
			}
		}

		return retList;
	}
	
	@GetMapping(value = "/comm/{service}/menus")
	public List<MenuDto> selMenus(@PathVariable(value = "service") String service, ParameterMap parameterMap) {
		return ManagedApiBeanMap.get(service).addSelectMenuHandler(parameterMap.getMap());
	}
	
	@GetMapping(value = "/comm/{service}/menu")
	public List<MenuDto> selMenu(@PathVariable(value = "service") String service, ParameterMap parameterMap) {
		return ManagedApiBeanMap.get(service).addSelectMenuHandler(parameterMap.getMap());
	}
	
	@PostMapping(value = "/comm/{service}/menu")
	public Object insMenu(@PathVariable(value = "service") String service, ParameterMap parameterMap) {
		MenuDto menuDto = (MenuDto)ObjectUtil.convertMapToObject(parameterMap.getMap(), new MenuDto());
		return ManagedApiBeanMap.get(service).addInsertMenuHandler(menuDto);
	}
	
	@PutMapping(value = "/comm/{service}/menu")
	public Object updMenu(@PathVariable(value = "service") String service, ParameterMap parameterMap) {
		MenuDto menuDto = (MenuDto)ObjectUtil.convertMapToObject(parameterMap.getMap(), new MenuDto());
		return ManagedApiBeanMap.get(service).addUpdateMenuHandler(menuDto);
	}
	
	@DeleteMapping(value = "/comm/{service}/menu")
	public Object delMenu(@PathVariable(value = "service") String service, ParameterMap parameterMap) {
		MenuDto menuDto = (MenuDto)ObjectUtil.convertMapToObject(parameterMap.getMap(), new MenuDto());
		return ManagedApiBeanMap.get(service).addDeleteMenuHandler(menuDto);
	}

}
