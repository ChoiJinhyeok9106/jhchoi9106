/**
 * 
 */
package kr.co.codefarm.svcm.additional.farmboard.controller;

import kr.co.codefarm.svcm.additional.farmboard.service.FarmBoardApiService;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/additional/farmboardApi")
public class FarmBoardApiController {
	
	@Autowired
	private FarmBoardApiService farmBoardApiService;
	
	/**************************************************************
	 * 팜보드 관련 부분
	 **************************************************************/
	@GetMapping({""})
	public List<Map<String, Object>> getFarmBoardMainList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		
		if(userDetailsImpl!=null) {
			parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
			parameterMap.put("INTERNAL_ID", userDetailsImpl.getInternalId());
			parameterMap.put("USER_NM", userDetailsImpl.getUserNm());
		}else {
			parameterMap.put("USER_ID", parameterMap.get("pAnswerId"));
			parameterMap.put("INTERNAL_ID", parameterMap.get("pAnswerId"));
		}
		List<Map<String, Object>> list = farmBoardApiService.getFarmBoardMainList(parameterMap.getMap());
		return list;
	}
	
	/**************************************************************
	 * 팜보드 리스트 가져오기
	 **************************************************************/
	@GetMapping({"/list"})
	public List<Map<String, Object>> getFarmBoardListList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		
		if(userDetailsImpl!=null) {
			parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
			parameterMap.put("INTERNAL_ID", userDetailsImpl.getInternalId());
			parameterMap.put("USER_NM", userDetailsImpl.getUserNm());
		}else {
			parameterMap.put("USER_ID", parameterMap.get("pAnswerId"));
			parameterMap.put("INTERNAL_ID", parameterMap.get("pAnswerId"));
		}
		
		if(parameterMap.getMap().containsKey("pFbSeq")) {
			String[] obj = parameterMap.get("pFbSeq").toString().split(",");
			parameterMap.put("pFbSeq", obj);
		}
		List<Map<String, Object>> list = farmBoardApiService.getFarmBoardListList(parameterMap.getMap());
		return list;
	}
	/**************************************************************
	 * 팜보드 카드 리스트 가져오기
	 **************************************************************/
	@GetMapping({"/card-list"})
	public List<Map<String, Object>> getFarmBoardCardList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		
		if(userDetailsImpl!=null) {
			parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
			parameterMap.put("INTERNAL_ID", userDetailsImpl.getInternalId());
			parameterMap.put("USER_NM", userDetailsImpl.getUserNm());
		}else {
			parameterMap.put("USER_ID", parameterMap.get("pAnswerId"));
			parameterMap.put("INTERNAL_ID", parameterMap.get("pAnswerId"));
		}
		
		if(parameterMap.getMap().containsKey("pFbSeq")) {
			String[] obj = parameterMap.get("pFbSeq").toString().split(",");
			parameterMap.put("pFbSeq", obj);
		}
		List<Map<String, Object>> list = farmBoardApiService.getFarmBoardCardList(parameterMap.getMap());
		return list;
	}
	
	/**************************************************************
	 * 팜보드 카드 리스트ALL 가져오기
	 **************************************************************/
	@GetMapping({"/card-all-list"})
	public List<Map<String, Object>> getFarmCardList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		
		if(userDetailsImpl!=null) {
			parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
			parameterMap.put("INTERNAL_ID", userDetailsImpl.getInternalId());
			parameterMap.put("USER_NM", userDetailsImpl.getUserNm());
		}else {
			parameterMap.put("USER_ID", parameterMap.get("pAnswerId"));
			parameterMap.put("INTERNAL_ID", parameterMap.get("pAnswerId"));
		}
		
		if(parameterMap.getMap().containsKey("pFbSeq")) {
			String[] obj = parameterMap.get("pFbSeq").toString().split(",");
			parameterMap.put("pFbSeq", obj);
		}
		List<Map<String, Object>> list = farmBoardApiService.getFarmCardList(parameterMap.getMap());
		return list;
	}
	/**************************************************************
	 * 팜보드 카드 Detail 리스트 가져오기
	 **************************************************************/
	@GetMapping({"/card-detail-list"})
	public List<Map<String, Object>> getFarmBoardCardDetailList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		
		if(userDetailsImpl!=null) {
			parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
			parameterMap.put("INTERNAL_ID", userDetailsImpl.getInternalId());
			parameterMap.put("USER_NM", userDetailsImpl.getUserNm());
		}else {
			parameterMap.put("USER_ID", parameterMap.get("pAnswerId"));
			parameterMap.put("INTERNAL_ID", parameterMap.get("pAnswerId"));
		}
		
		if(parameterMap.getMap().containsKey("pUserId")) {
			String[] obj = parameterMap.get("pUserId").toString().split(",");
			parameterMap.put("pUserId", obj);
		}
		
		if(parameterMap.getMap().containsKey("pFbSeq")) {
			String[] obj = parameterMap.get("pFbSeq").toString().split(",");
			parameterMap.put("pFbSeq", obj);
		}
		
		if(parameterMap.getMap().containsKey("pListSeq")) {
			String[] obj = parameterMap.get("pListSeq").toString().split(",");
			parameterMap.put("pListSeq", obj);
		}
		
		if(parameterMap.getMap().containsKey("pCardSeq")) {
			String[] obj = parameterMap.get("pCardSeq").toString().split(",");
			parameterMap.put("pCardSeq", obj);
		}
		List<Map<String, Object>> list = farmBoardApiService.getFarmBoardCardDetailList(parameterMap.getMap());
		return list;
	}
	
	
	@PostMapping("")
	public Object insertFarmBoard(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		int insCnt = 0;
		
		Map<String, Object> fbRow = farmBoardApiService.insertFarmBoard(parameterMap.getMap());
		
		if(!fbRow.get("FB_SEQ").equals(null) || !fbRow.get("FB_SEQ").equals("")) {
			insCnt ++;

			fbRow.put("FB_SEQ", fbRow.get("FB_SEQ"));
			fbRow.put("FB_USER_ID", userDetailsImpl.getInternalId());
			fbRow.put("CHAMYEOJA_ID", userDetailsImpl.getInternalId());
			fbRow.put("LIST_GWANRI_AUTH_YN", "Y");
			fbRow.put("CHODAE_TITLE", "");
			fbRow.put("CHAMYEOJA_DESC", "");
			fbRow.put("CHAMYEO_STATUS_GB", "02");
			fbRow.put("BOOKMARK_YN", "N");

			insCnt += farmBoardApiService.insertFarmBoardChamyeoja(fbRow);
		}
		
		return insCnt;
	}
	
	@PostMapping("/ncu-farmboard")
	@Transactional
	public Object insertNcuFarmBoard(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		int insCnt = 0;
		/* 1.팜보드 INSERT */
		Map<String, Object> fbRow = farmBoardApiService.insertFarmBoard(parameterMap.getMap());
		
		
		
		fbRow.put("FB_SEQ", fbRow.get("FB_SEQ"));
		fbRow.put("FB_USER_ID", fbRow.get("USER_ID"));
		fbRow.put("CHAMYEOJA_ID", fbRow.get("USER_ID"));
		fbRow.put("LIST_GWANRI_AUTH_YN", "Y");
		fbRow.put("CHODAE_TITLE", "");
		fbRow.put("CHAMYEOJA_DESC", "");
		fbRow.put("CHAMYEO_STATUS_GB", "02");
		fbRow.put("BOOKMARK_YN", "N");
		fbRow.put("SYSTEM_ID", fbRow.get("USER_ID"));
		
		/* 2.팜보드 참여자 INSERT */
		/* 참여자 존재하는지 확인*/
		List<Map<String, Object>> chamyeojaList = farmBoardApiService.getFarmboardChamyeoja(fbRow);
		
		if(chamyeojaList.size() == 0) {
			insCnt += farmBoardApiService.insertFarmBoardChamyeoja(fbRow);
		}
		
	
		/* 3.팜보드 리스트 INSERT */
		/* 리스트SEQ : 1(공지사항)이 존재하는지 확인*/
		fbRow.put("LIST_SEQ", 1);
		List<Map<String, Object>> fbList = farmBoardApiService.getFbList(fbRow);
		if(fbList.size() == 0) {
			insCnt += farmBoardApiService.insertFarmBoardList(fbRow);
		}
		
		
	
		/* 4.팜보드 카드 INSERT */
		/* 리스트SEQ 1 , 카드 SEQ 1 이 존재하는지 확인 */
		fbRow.put("LIST_SEQ", 1);
		fbRow.put("CARD_SEQ", 1);
		List<Map<String, Object>> fbCardList = farmBoardApiService.getFbCard(fbRow);
		if(fbCardList.size() == 0) {
			fbRow.put("CARD_TITLE", fbRow.get("LIST_NM"));
			fbRow.put("CARD_DESC", fbRow.get("LIST_NM"));
			insCnt += farmBoardApiService.insertFarmBoardCard(fbRow);
		}
		
		return fbRow;
	}
	
	@PostMapping("/ncu-farmboard-chamyeoja")
	@Transactional
	public Object insertNcuFarmBoardListChamyeoJa(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		int insCnt = 0;
		List<Map<String, Object>> list = (List<Map<String, Object>>) parameterMap.get("CHAMYEOJA_LIST");
		
		for(int i = 0 ; i < list.size() ; i++) {
			Map<String,Object> sendMap = new HashMap<String,Object>();
			sendMap.put("CHAMYEOJA_ID", list.get(i).get("ANSWER_ID"));
			sendMap.put("FB_SEQ", list.get(i).get("FB_SEQ"));
			sendMap.put("USER_ID", list.get(i).get("DAMDANGJA_ID"));
			
			List<Map<String, Object>> fbChamyeojaList = farmBoardApiService.getFarmboardListChamyeoja(sendMap);
			if(fbChamyeojaList.size() == 0) {
				List<Map<String, Object>> fbCardChamyeojaList = farmBoardApiService.getFarmboardListChamyeoja(sendMap);
				if(fbCardChamyeojaList.size() == 0) {
					Map<String,Object> fbChamyeoJa = new HashMap<String,Object>();
					fbChamyeoJa.put("FB_SEQ", list.get(i).get("FB_SEQ"));
					fbChamyeoJa.put("FB_USER_ID", list.get(i).get("DAMDANGJA_ID"));
					fbChamyeoJa.put("USER_ID", list.get(i).get("ANSWER_ID"));
					fbChamyeoJa.put("LIST_GWANRI_AUTH_YN", "N");
					fbChamyeoJa.put("CHODAE_TITLE", "");
					fbChamyeoJa.put("CHAMYEOJA_DESC", "");
					fbChamyeoJa.put("CHAMYEO_STATUS_GB", "02");
					fbChamyeoJa.put("BOOKMARK_YN", "N");
					fbChamyeoJa.put("SYSTEM_ID",parameterMap.get("SYSTEM_ID"));
					fbChamyeoJa.put("SYSTEM_IP",parameterMap.get("SYSTEM_IP"));
					fbChamyeoJa.put("SYSTEM_PGM",parameterMap.get("SYSTEM_PGM"));
					
					/* 2.팜보드 참여자 INSERT */
					insCnt += farmBoardApiService.insertFarmBoardChamyeoja(fbChamyeoJa);
					
					/* 3.팜보드 리스트 참여자 */
					/* 비교과 리스트 SEQ : 1 은 공지사항*/
					fbChamyeoJa.put("LIST_SEQ", "1");
					fbChamyeoJa.put("CARD_GWANRI_AUTH_YN", "N");
					insCnt += farmBoardApiService.insertFarmBoardListChamyeoja(fbChamyeoJa);
				}
			}
		}
		
		return insCnt;
	}
	/*@PathVariable("pMileageUnyeongSeq") String pMileageUnyeongSeq*/
	@DeleteMapping("/ncu-farmboard-chamyeoja/{pFbUserId}/{pFbSeq}/{pChamyeojaId}")
	public Object deleteNcuFarmBoardChamyeoja( @PathVariable("pFbUserId") String pFbUserId
											 , @PathVariable("pFbSeq") String pFbSeq
											 , @PathVariable("pChamyeojaId") String pChamyeojaId
											 , ParameterMap parameterMap) {
		
		parameterMap.getMap().put("FB_USER_ID", pFbUserId);
		parameterMap.getMap().put("FB_SEQ", pFbSeq);
		parameterMap.getMap().put("CHAMYEOJA_ID", pChamyeojaId);
		
		int delCnt = farmBoardApiService.deleteNcuFarmBoardChamyeoja(parameterMap.getMap());
		
		return delCnt;
	}
	
	@DeleteMapping("")
	public Object deleteFarmBoard(ParameterMap parameterMap) {
		int delCnt = farmBoardApiService.deleteFarmBoard(parameterMap.getMap());
		
		return delCnt;
	}
	
}
