/**
 * 
 */
package kr.co.codefarm.svcm.additional.farmboard.controller;

import kr.co.codefarm.svcm.additional.farmboard.service.FarmBoardService;
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
@RequestMapping(value = "/additional/farmboard")
public class FarmBoardController {
	
	@Autowired
	private FarmBoardService farmBoardService;

	/**************************************************************
	 * 팜보드 초대 카운트 부분
	 **************************************************************/
	@GetMapping({"invited-cnt"})
	public List<Map<String, Object>> getInvitedCnt(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
		
		List<Map<String, Object>> list = farmBoardService.getInvitedCnt(parameterMap.getMap());
		return list;
	}
	
	/**************************************************************
	 * 팜보드 관련 부분
	 **************************************************************/
	@GetMapping({""})
	public List<Map<String, Object>> getFarmBoardMainList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		parameterMap.put("USER_ID", userDetailsImpl.getInternalId());
		parameterMap.put("USER_NM", userDetailsImpl.getUserNm());

		List<Map<String, Object>> list = farmBoardService.getFarmBoardMainList(parameterMap.getMap());
		return list;
	}
	
	@PostMapping("")
	public Object insertFarmBoard(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		int insCnt = 0;
		int labelInsCnt = 0;
		
		Map<String, Object> farmboardMap = (Map<String, Object>) parameterMap.get("FARMBOARD_LIST");

		farmboardMap.put("USER_ID", userDetailsImpl.getInternalId());
		farmboardMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
		farmboardMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
		farmboardMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
		
		List labelList = (List) parameterMap.get("LABEL_LIST");
		
		Map<String, Object> fbRow = farmBoardService.insertFarmBoard(farmboardMap);
		
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

			insCnt += farmBoardService.insertFarmBoardChamyeoja(fbRow);
		}
		
		for(int i = 0; i < labelList.size(); i++) {
			Map labelParamMap = (Map<String, Object>) labelList.get(i);
			
			if(!labelParamMap.get("LABEL_NM").equals("")) {
				labelParamMap.put("USER_ID", fbRow.get("USER_ID"));
				labelParamMap.put("FB_SEQ", fbRow.get("FB_SEQ"));
				labelParamMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
				labelParamMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
				labelParamMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
				
				labelInsCnt = farmBoardService.insertLabel(labelParamMap);
			}

			labelInsCnt += labelInsCnt;
		}
		
		insCnt = insCnt + labelInsCnt;
		
		return insCnt;
	}
	
	@PutMapping("")
	public Object updateFarmBoard(ParameterMap parameterMap) {
		int updCnt = 0;
		int labelDelCnt = 0;
		int labelUpdCnt = 0;
		
		Map<String, Object> farmboardMap = (Map<String, Object>) parameterMap.get("FARMBOARD_LIST");
		
		farmboardMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
		farmboardMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
		farmboardMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
		
		List labelList = (List) parameterMap.get("LABEL_LIST");
		
		updCnt = farmBoardService.updateFarmBoard(farmboardMap);
		
		if(labelList.size() > 0) {
			labelDelCnt = farmBoardService.deleteLabel(farmboardMap);
		}
		
		for(int i = 0; i < labelList.size(); i++) {
			Map labelParamMap = (Map<String, Object>) labelList.get(i);
			
			if((Long) labelParamMap.get("ROW_TYPE") == 8) {
				labelDelCnt += farmBoardService.deleteFarmBoardCardDetail(labelParamMap);
			}
			
			if(!labelParamMap.get("LABEL_NM").equals("") && (Long) labelParamMap.get("ROW_TYPE") != 8) {
				labelParamMap.put("USER_ID", farmboardMap.get("USER_ID"));
				labelParamMap.put("FB_SEQ", farmboardMap.get("FB_SEQ"));
				labelParamMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
				labelParamMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
				labelParamMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
				
				labelUpdCnt = farmBoardService.insertLabel(labelParamMap);
			}

			labelUpdCnt += labelUpdCnt;
		}
		
		updCnt = updCnt + labelUpdCnt + labelDelCnt;
		
		return updCnt;
	}
	
	@DeleteMapping("")
	public Object deleteFarmBoard(ParameterMap parameterMap) {
		int delCnt = 0;
		
		delCnt = farmBoardService.deleteFarmBoardCardDetailItem(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCardDetail(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCardChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCard(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardListChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardList(parameterMap.getMap());
		delCnt += farmBoardService.deleteLabel(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoard(parameterMap.getMap());
		
		return delCnt;
	}
	
	//팜보드 탈퇴
	@DeleteMapping("/secession")
	public Object secessionFarmBoard(ParameterMap parameterMap) {
		int delCnt = 0;
		
		delCnt = farmBoardService.deleteFarmBoardCardChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardListChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardChamyeoja(parameterMap.getMap());
		
		return delCnt;
	}
	
	/**************************************************************
	 * 라벨 관련 부분
	 **************************************************************/
	@GetMapping({"/label"})
	public List<Map<String, Object>> getLabelList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = farmBoardService.getLabelList(parameterMap.getMap());
		return list;
	}
	
	@DeleteMapping("/label")
	public Object deleteLabel(ParameterMap parameterMap) {
		int delCnt = 0;
		
		delCnt = farmBoardService.deleteLabel(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCardDetail(parameterMap.getMap());
		
		return delCnt;
	}
	
	/**************************************************************
	 * 보드 리스트 관련 부분
	 **************************************************************/
	@GetMapping({"/list"})
	public List<Map<String, Object>> getFarmBoardList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		parameterMap.put("INTERNAL_ID", userDetailsImpl.getInternalId());
		List<Map<String, Object>> list = farmBoardService.getFarmBoardList(parameterMap.getMap());
		return list;
	}
	
	@Transactional
	@PostMapping("/list")
	public Object insertFarmBoardList(ParameterMap parameterMap) {
		Map<String, Object> insObj = new HashMap();
		
		insObj = farmBoardService.insertFarmBoardList(parameterMap.getMap());
		
		return insObj;
	}
	
	@Transactional
	@PutMapping("/list")
	public Object updateFarmBoardList(ParameterMap parameterMap) {
		int updCnt = 0;
		
		updCnt = farmBoardService.updateFarmBoardList(parameterMap.getMap());
		
		return updCnt;
	}
	
	@Transactional
	@PutMapping("/list-ord")
	public Object updateFarmBoardListOrd(ParameterMap parameterMap) {
		int executeCnt = 0;
		
		List farmboardList = (List) parameterMap.get("JSON_DATA_LIST");
		
		for(int i = 0; i < farmboardList.size(); i++) {
			Map paramMap = (Map<String, Object>) farmboardList.get(i);

			paramMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
			paramMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
			paramMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));

			executeCnt += farmBoardService.updateFarmBoardList(paramMap);
		}
		
		return executeCnt;
	}
	
	@Transactional
	@DeleteMapping("/list")
	public Object deleteFarmBoardList(ParameterMap parameterMap) {
		int delCnt = 0;
		
		delCnt = farmBoardService.deleteFarmBoardCardDetailItem(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCardDetail(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCardChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCard(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardListChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardList(parameterMap.getMap());
		
		return delCnt;
	}

	/**************************************************************
	 * 보드 참여자 관련 부분
	 **************************************************************/
	@GetMapping({"/chamyeoja"})
	public List<Map<String, Object>> getFarmBoardChamyeojaList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = farmBoardService.getFarmBoardChamyeojaList(parameterMap.getMap());
		return list;
	}
	
	@PutMapping("/chamyeoja")
	public Object updateFarmBoardChamyeoja(ParameterMap parameterMap) {
		int updCnt = 0;
		
		updCnt = farmBoardService.updateFarmBoardChamyeoja(parameterMap.getMap());
		
		return updCnt;
	}

	@PutMapping("/bookmark")
	public Object updateBookmark(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		int updCnt = 0;
		
		parameterMap.put("CHAMYEOJA_ID", userDetailsImpl.getUserId());
		
		updCnt = farmBoardService.updateBookmark(parameterMap.getMap());
		
		return updCnt;
	}
	
	@PutMapping("/answ-invitiation")
	public Object updateAnswInvitiation(ParameterMap parameterMap) {
		int updCnt = 0;
		
		updCnt = farmBoardService.updateAnswInvitiation(parameterMap.getMap());
		
		return updCnt;
	}
	
	@DeleteMapping("/chamyeoja")
	public Object deleteFarmBoardChamyeoja(ParameterMap parameterMap) {
		int delCnt = 0;

		delCnt = farmBoardService.deleteFarmBoardCardChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardListChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardChamyeoja(parameterMap.getMap());
		
		return delCnt;
	}
	
	/**************************************************************
	 * 리스트 참여자 관련 부분
	 **************************************************************/
	@GetMapping({"/list-chamyeoja"})
	public List<Map<String, Object>> getFarmBoardListChamyeojaList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = farmBoardService.getFarmBoardListChamyeojaList(parameterMap.getMap());
		return list;
	}
	
	@PostMapping("/list-chamyeoja")
	public Object insertFarmBoardListChamyeoja(ParameterMap parameterMap) {
		int insCnt = 0;
		
		insCnt = farmBoardService.insertFarmBoardListChamyeoja(parameterMap.getMap());
		
		return insCnt;
	}
	
	@PutMapping("/list-chamyeoja")
	public Object updateFarmBoardListChamyeoja(ParameterMap parameterMap) {
		int updCnt = 0;
		
		updCnt = farmBoardService.updateFarmBoardListChamyeoja(parameterMap.getMap());
		
		return updCnt;
	}
	
	@DeleteMapping("/list-chamyeoja")
	public Object deleteFarmBoardListChamyeoja(ParameterMap parameterMap) {
		int delCnt = 0;

		delCnt = farmBoardService.deleteFarmBoardCardChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardListChamyeoja(parameterMap.getMap());
		
		return delCnt;
	}
	
	/**************************************************************
	 * 초대 관련 부분
	 **************************************************************/
	@PostMapping("/send-invitation")
	public Object sendInvitation(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		int insCnt = 0;
		
		List inviteList = (List) parameterMap.get("INVITE_LIST");
		
		for(int i = 0; i < inviteList.size(); i++) {
			Map inviteParamMap = (Map<String, Object>) inviteList.get(i);
			
			String pUserId = (String) inviteParamMap.get("USER_ID");
			
			//팜보드 사용자 아이디와 참여자 아이디가 다르면 insert
			if(!pUserId.equals((String) parameterMap.get("FB_USER_ID"))) {
				inviteParamMap.put("FB_USER_ID", parameterMap.get("FB_USER_ID"));
				inviteParamMap.put("FB_SEQ", parameterMap.get("FB_SEQ"));
				inviteParamMap.put("CHODAE_TITLE", parameterMap.get("CHODAE_TITLE"));
				inviteParamMap.put("LIST_GWANRI_AUTH_YN", "N");
				inviteParamMap.put("BOOKMARK_YN", "N");
				inviteParamMap.put("CHAMYEO_STATUS_GB", "01");
				inviteParamMap.put("CHAMYEOJA_DESC", parameterMap.get("CHAMYEOJA_DESC"));
				inviteParamMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
				inviteParamMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
				inviteParamMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
				
				insCnt += farmBoardService.insertFarmBoardChamyeoja(inviteParamMap);
			}
			//팜보드 사용자 아이디와 참여자 아이디가 같으면 insCnt에 카운트 값만 증가
			else {
				insCnt ++;
			}
			
		}
		
		return insCnt;
	}
	
	/**************************************************************
	 * 팜보드 카드 관련 부분
	 **************************************************************/
	@GetMapping({"/card"})
	public List<Map<String, Object>> getFarmBoardCardList(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		parameterMap.put("INTERNAL_ID", userDetailsImpl.getInternalId());
		List<Map<String, Object>> list = farmBoardService.getFarmBoardCardList(parameterMap.getMap());
		return list;
	}

	@GetMapping({"/card-label"})
	public List<Map<String, Object>> getFarmBoardCardLabelList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = farmBoardService.getFarmBoardCardLabelList(parameterMap.getMap());
		return list;
	}
	
	@Transactional
	@PostMapping("/card")
	public Object insertFarmBoardCard(ParameterMap parameterMap) {
		Map<String, Object> insObj = new HashMap();
		
		insObj = farmBoardService.insertFarmBoardCard(parameterMap.getMap());
		
		return insObj;
	}
	
	@Transactional
	@PutMapping("/card")
	public Object updateFarmBoardCard(ParameterMap parameterMap) {
		int updCnt = 0;
		
		updCnt = farmBoardService.updateFarmBoardCard(parameterMap.getMap());
		
		return updCnt;
	}
	
	@Transactional
	@PutMapping("/card-ord")
	public Object updateFarmBoardCardOrd(ParameterMap parameterMap) {
		int executeCnt = 0;
		
		List farmboardCardList = (List) parameterMap.get("JSON_DATA_LIST");
		
		for(int i = 0; i < farmboardCardList.size(); i++) {
			Map paramMap = (Map<String, Object>) farmboardCardList.get(i);

			paramMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
			paramMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
			paramMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));

			executeCnt += farmBoardService.updateFarmBoardCard(paramMap);
		}
		
		return executeCnt;
	}
	
	@DeleteMapping("/card")
	public Object deleteFarmBoardCard(ParameterMap parameterMap) {
		int delCnt = 0;

		delCnt = farmBoardService.deleteFarmBoardCardDetailItem(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCardDetail(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCardChamyeoja(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCard(parameterMap.getMap());
		
		return delCnt;
	}
	
	/**************************************************************
	 * 팜보드 카드 참여자 관련 부분
	 **************************************************************/
	@GetMapping({"/card-chamyeoja"})
	public List<Map<String, Object>> getFarmBoardCardChamyeojaList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = farmBoardService.getFarmBoardCardChamyeojaList(parameterMap.getMap());
		return list;
	}

	@PostMapping("/card-chamyeoja")
	public Object insertFarmBoardCardChamyeoja(ParameterMap parameterMap) {
		int insCnt = 0;
		
		insCnt = farmBoardService.insertFarmBoardCardChamyeoja(parameterMap.getMap());
		
		return insCnt;
	}
	
	@DeleteMapping("/card-chamyeoja")
	public Object deleteFarmBoardCardChamyeoja(ParameterMap parameterMap) {
		int delCnt = 0;

		delCnt = farmBoardService.deleteFarmBoardCardChamyeoja(parameterMap.getMap());
		
		return delCnt;
	}
	
	/**************************************************************
	 * 팜보드 카드 상세 관련 부분
	 **************************************************************/
	@GetMapping({"/card-detail"})
	public List<Map<String, Object>> getFarmBoardCardDetailList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = farmBoardService.getFarmBoardCardDetailList(parameterMap.getMap());
		return list;
	}
	
	@PostMapping("/card-detail")
	public Object insertFarmBoardCardDetail(ParameterMap parameterMap) {
		Map<String, Object> list = farmBoardService.insertFarmBoardCardDetail(parameterMap.getMap());

		int executeCnt = 0;

		//ADD_FILE 업데이트
		executeCnt+= farmBoardService.updateAddCardDetailFile(parameterMap.getMap());
		
		return list;
	}
	
	@PutMapping("/card-detail")
	public Object updateFarmBoardCardDetail(ParameterMap parameterMap) {
		int updCnt = 0;
		
		updCnt = farmBoardService.updateFarmBoardCardDetail(parameterMap.getMap());
		
		return updCnt;
	}
	
	@PostMapping("/card-check-list")
	public Object insertFarmBoardCardCheckList(ParameterMap parameterMap) {
		int executeCnt = 0;
		
		Map<String, Object> list = farmBoardService.insertFarmBoardCardDetail(parameterMap.getMap());
		
		Map<String, Object> chkItemLists = (Map<String, Object>) parameterMap.get("CHK_ITEM_LIST");
		List chkItemList = (List) chkItemLists.get("data");
		
		for(int i = 0 ; i < chkItemList.size(); i++) {
			Map insertChkItem = (Map<String, Object>) chkItemList.get(i);
			if(!insertChkItem.get("ITEM_NM").equals("")) {
				insertChkItem.put("CARD_DETAIL_SEQ", list.get("CARD_DETAIL_SEQ"));
				insertChkItem.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
				insertChkItem.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
				insertChkItem.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));

				executeCnt += farmBoardService.insertFarmBoardCardDetailItem(insertChkItem);
			}
		}
		
		return executeCnt;
	}
	
	@PutMapping("/card-check-list")
	public Object updateFarmBoardCardCheckList(ParameterMap parameterMap) {
		int executeCnt = 0;
		
		executeCnt = farmBoardService.updateFarmBoardCardDetail(parameterMap.getMap());
		
		//상세 항목 모두 삭제
		executeCnt += farmBoardService.deleteFarmBoardCardDetailItem(parameterMap.getMap());
		
		Map<String, Object> chkItemLists = (Map<String, Object>) parameterMap.get("CHK_ITEM_LIST");
		List chkItemList = (List) chkItemLists.get("data");
		
		for(int i = 0 ; i < chkItemList.size(); i++) {
			Map updateChkItem = (Map<String, Object>) chkItemList.get(i);
		
			updateChkItem.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
			updateChkItem.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
			updateChkItem.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
			
			executeCnt += farmBoardService.insertFarmBoardCardDetailItem(updateChkItem);
		}
		
		return executeCnt;
	}
	
	@PutMapping("/card-daepyo-img")
	public Object updateFarmBoardCardDaepyoImg(ParameterMap parameterMap) {
		int updCnt = 0;
		
		Map<String, Object> imgParamMap = new HashMap<String, Object>();

		imgParamMap.put("USER_ID", parameterMap.get("USER_ID"));
		imgParamMap.put("FB_SEQ", parameterMap.get("FB_SEQ"));
		imgParamMap.put("LIST_SEQ", parameterMap.get("LIST_SEQ"));
		imgParamMap.put("CARD_SEQ", parameterMap.get("CARD_SEQ"));
		imgParamMap.put("DAEPYO_IMG_YN", "N");
		imgParamMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
		imgParamMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
		imgParamMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));
		
		//전체 DAEPYO_IMG_YN 를 N으로 update
		updCnt = farmBoardService.updateFarmBoardCardDaepyoImg(imgParamMap);
		
		updCnt += farmBoardService.updateFarmBoardCardDaepyoImg(parameterMap.getMap());
		
		return updCnt;
	}
	
	@DeleteMapping("/card-detail")
	public Object deleteFarmBoardCardDetail(ParameterMap parameterMap) {
		int delCnt = 0;

		delCnt = farmBoardService.deleteFarmBoardCardDetailItem(parameterMap.getMap());
		delCnt += farmBoardService.deleteFarmBoardCardDetail(parameterMap.getMap());
		
		return delCnt;
	}
	
	/**************************************************************
	 * 팜보드 카드 상세 항목 관련 부분
	 **************************************************************/
	@GetMapping({"/card-detail-item"})
	public List<Map<String, Object>> getFarmBoardCardDetailItemList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = farmBoardService.getFarmBoardCardDetailItemList(parameterMap.getMap());
		return list;
	}
	
	@PostMapping("/card-detail-item")
	public Object insertFarmBoardCardDetailItem(ParameterMap parameterMap) {
		int insCnt = 0;
		
		insCnt = farmBoardService.insertFarmBoardCardDetailItem(parameterMap.getMap());
		
		return insCnt;
	}
	
	@PutMapping("/card-detail-item")
	public Object updateFarmBoardCardDetailItem(ParameterMap parameterMap, @AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl) {
		parameterMap.put("INTERNAL_ID", userDetailsImpl.getInternalId());
		
		int updCnt = 0;
		
		updCnt = farmBoardService.updateFarmBoardCardDetailItem(parameterMap.getMap());
		
		return updCnt;
	}
	
	@DeleteMapping("/card-detail-item")
	public Object deleteFarmBoardCardDetailItem(ParameterMap parameterMap) {
		int delCnt = 0;

		delCnt = farmBoardService.deleteFarmBoardCardDetailItem(parameterMap.getMap());
		
		return delCnt;
	}
}
