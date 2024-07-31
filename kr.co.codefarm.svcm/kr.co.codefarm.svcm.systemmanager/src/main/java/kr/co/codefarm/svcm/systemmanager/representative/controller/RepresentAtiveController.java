/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.representative.controller;

import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.systemmanager.representative.service.RepresentAtiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/system-manager/represent-ative")
public class RepresentAtiveController {
	
	@Autowired
	private RepresentAtiveService representAtivService;
	
	//service
	@GetMapping("")
	public List<Map<String, Object>> representAtivSelect(ParameterMap parameterMap) {
		String pSiteSeq = (String) parameterMap.get("pSiteSeq");
		
		List<Map<String, Object>> list = representAtivService.getRepresentAtivList(pSiteSeq);
		
		return list;
	}
	
	@GetMapping("/check")
	public int representAtivCheck(ParameterMap parameterMap) {
   		String pSiteSeq = (String) parameterMap.get("pSiteSeq");
		String pDaepyojaGb = (String) parameterMap.get("pDaepyojaGb");
		String Frdt = (String) parameterMap.get("pFrdt");
		String Todt = (String) parameterMap.get("pTodt");
	
		List<Map<String, Object>> list = representAtivService.getRepresentAtivCheck(pSiteSeq, pDaepyojaGb, Frdt, Todt);
		return list.size();
	}
	
	@PostMapping({""}) 
	public Object insertRepresentAtiv(ParameterMap parameterMap) { 
		int insCnt = representAtivService.insertRepresentAtiv(parameterMap.getMap()); 
	  	return insCnt;
	}
	 
	 
	@PutMapping({""})
	public Object updateRepresentAtiv(ParameterMap parameterMap) {
		int insCnt = representAtivService.updateRepresentAtiv(parameterMap.getMap());
		return insCnt;
	}
	 
	 
	@DeleteMapping({""})
	public Object deleteRepresentAtiv(ParameterMap parameterMap) {
		String pSiteSeq 		= (String)  parameterMap.get("pSiteSeq");
		String pDaepyojaGb 		= (String)  parameterMap.get("pDaepyojaGb");
		String pDaepyojaSeq 	= (String)  parameterMap.get("pDaepyojaSeq");
		
		int delCnt = representAtivService.deleteRepresentAtiv(pSiteSeq, pDaepyojaGb, pDaepyojaSeq);
		return delCnt;
	}
}
