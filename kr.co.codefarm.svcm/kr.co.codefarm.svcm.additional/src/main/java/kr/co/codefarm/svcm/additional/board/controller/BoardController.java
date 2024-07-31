/**
 * 
 */
package kr.co.codefarm.svcm.additional.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.codefarm.svcm.additional.board.service.BoardService;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.service.mail.EmailService;
import kr.co.codefarm.svcm.systemmanager.user.service.UserApiService;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/additional/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserApiService userApiService;

	// 게시판(ADD_BOARD)
	// 게시판 전체 조회
	@GetMapping({ "" })
	public List<Map<String, Object>> getBoardList(ParameterMap parameterMap) {
		String pBoardGb = parameterMap.get("pBoardGb").toString();
		String pUseYn = parameterMap.get("pUseYn").toString();
		String pSearchType = parameterMap.get("pSearchType").toString();
		String pSearchArgv = parameterMap.get("pSearchArgv").toString();
		String pPageNo = parameterMap.get("pPageNo").toString();
		String pRowCount = parameterMap.get("pRowCount").toString();
		// pPageCount = "5";
		List<Map<String, Object>> list = boardService.getBoardList(pBoardGb, pUseYn, pSearchType, pSearchArgv, pPageNo,
				pRowCount);
		return list;
	}

	@GetMapping("/{boardSeq}")
	public Object getBoard(@PathVariable(value = "boardSeq") String boardSeq, ParameterMap parameterMap) {
//		if (boardSeq.isEmpty()) {
//		}

		Object board = boardService.getBoard(boardSeq);
		return board;
	}

	/*
	 * @PostMapping("") public Object insertBoard(ParameterMap parameterMap) { int
	 * insCnt = boardService.insertBoard(parameterMap.getMap()); return insCnt; }
	 */

	@PostMapping("")
	public Object insertBoard(ParameterMap parameterMap) {
		Map<String, Object> insObj = boardService.insertBoard(parameterMap.getMap());
		return insObj;
	}

	@PutMapping("")
	public Object updateBoard(ParameterMap parameterMap) {
		int insCnt = boardService.updateBoard(parameterMap.getMap());
		return insCnt;
	}

	@DeleteMapping("/{boardSeq}")
	public Object deleteBoardMain(@PathVariable(value = "boardSeq") String boardSeq, ParameterMap parameterMap) {
//		if (boardSeq.isEmpty()) {
//
//		}

		int delCnt = boardService.deleteBoardMain(boardSeq);
		return delCnt;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 게시판 그룹(ADD_BOARD_POST)
	// 게시판 그룹 전체 조회
	@GetMapping({ "/post" })
	public List<Map<String, Object>> getBoardPostList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = boardService.getBoardPostList(parameterMap.getMap());
		return list;
	}

	@GetMapping("/post/{postNo}")
	public Object getBoardPost(@PathVariable(value = "postNo") String postNo, ParameterMap parameterMap) {
//		if (postNo.isEmpty()) {
//		}

		Object boardPost = boardService.getBoardPost(postNo);
		return boardPost;
	}

	@GetMapping({ "/post/contents" })
	public Object getBoardPostContents(ParameterMap parameterMap) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Object boardPost = boardService.getBoardPostContents(parameterMap.getMap());
		return boardPost;
	}

	@PostMapping({ "/post" })
	public Object insertBoardPost(ParameterMap parameterMap) {
		Map<String, Object> insObj = boardService.insertBoardPost(parameterMap.getMap());
		return insObj;
	}

	@PutMapping({ "/post" })
	public Object updateBoardPost(HttpServletRequest request, ParameterMap parameterMap) {

		int insCnt = boardService.updateBoardPost(parameterMap.getMap());
		return insCnt;
	}

	@PutMapping({ "/post/count" })
	public Object updateBoardPostCnt(ParameterMap parameterMap) {
		int insCnt = boardService.updateBoardPostCnt(parameterMap.getMap());
		return insCnt;
	}

	@PutMapping({ "/post/cm" })
	public Object updateBoardPostCmCnt(ParameterMap parameterMap) {
		int insCnt = boardService.updateBoardPostCmCnt(parameterMap.getMap());
		return insCnt;
	}

	/*
	 * @DeleteMapping("/post/{postNo}") public Object
	 * deleteBoardPost(@PathVariable(value="postNo") String postNo, ParameterMap
	 * parameterMap) { if(postNo.isEmpty()) {
	 * 
	 * } int delCnt = boardService.deleteBoardPost(postNo); return delCnt; }
	 */

	@DeleteMapping("/post")
	public Object deleteBoardPost(String postNo, ParameterMap parameterMap) {
		String pPostNo = (String) parameterMap.get("pPostNo");

		int delCnt = boardService.deleteBoardPost(pPostNo);
		return delCnt;
	}

	/*
	 * @DeleteMapping("/post/remove/{postNo}") public Object
	 * removeBoardPost(@PathVariable(value="postNo") String postNo, ParameterMap
	 * parameterMap) { if(postNo.isEmpty()) {
	 * 
	 * } int delCnt = boardService.removeBoardPost(postNo); return delCnt; }
	 */

	@DeleteMapping("/post/remove")
	public Object removeBoardPost(String postNo, ParameterMap parameterMap) {
		String pPostNo = (String) parameterMap.get("pPostNo");

		int delCnt = boardService.removeBoardPost(pPostNo);
		return delCnt;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 게시판 댓글(ADD_BOARD_CM)
	// 게시판 댓글 전체 조회
	@GetMapping({ "/cm" })
	public List<Map<String, Object>> getBoardCmList(ParameterMap parameterMap) {
		String pPostNo = (String) parameterMap.get("pPostNo");
		String pPageNo = (String) parameterMap.get("pPageNo");
		String pRowCount = (String) parameterMap.get("pRowCount");
		// pPageCount = "5";

		List<Map<String, Object>> list = boardService.getBoardCmList(pPostNo, pPageNo, pRowCount);
		return list;
	}

	@PostMapping({ "/cm" })
	public Object insertBoardCm(ParameterMap parameterMap) {
		int insCnt = boardService.insertBoardCm(parameterMap.getMap());
		return insCnt;
	}

	@PutMapping({ "/cm" })
	public Object updateBoardCm(ParameterMap parameterMap) {
		int insCnt = boardService.updateBoardCm(parameterMap.getMap());
		return insCnt;
	}

	@DeleteMapping({ "/cm" })
	public Object deleteBoardCm(String boardSeq, ParameterMap parameterMap) {

		String pPostNo = (String) parameterMap.get("pPostNo");
		String pCmSeq = (String) parameterMap.get("pCmSeq");

		int delCnt = boardService.deleteBoardCm(pPostNo, pCmSeq);
		return delCnt;
	}

	@DeleteMapping({ "/cm/remove" })
	public Object removeBoardCm(String boardSeq, ParameterMap parameterMap) {

		String pPostNo = (String) parameterMap.get("pPostNo");
		String pCmSeq = (String) parameterMap.get("pCmSeq");

		int delCnt = boardService.deleteBoardCm(pPostNo, pCmSeq);
		return delCnt;
	}

	// QnA 답글 찾기
	@GetMapping({"/answer"})
	public List<Map<String, Object>> answerPostList(ParameterMap parameterMap) {
		List<Map<String, Object>> list = boardService.answerPostList(parameterMap.getMap());
		return list;
	}

	// 게시판 좋아요(ADD_POST_LIKE)
	// 게시판 좋아요 중복 체크
	@GetMapping({ "/like" })
	public List<Map<String, Object>> checkPostLike(ParameterMap parameterMap) {
		List<Map<String, Object>> list = boardService.checkPostLike(parameterMap.getMap());
		return list;
	}

	// 게시판 좋아요 등록
	@PostMapping({ "/like" })
	public Object insertBoardLike(ParameterMap parameterMap) {
		int insCnt = boardService.insertBoardLike(parameterMap.getMap());
		return insCnt;
	}

	// 게시판 좋아요 취소
	@DeleteMapping("/like")
	public Object deleteBoardLike(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap){
		parameterMap.put("USER_ID",  userDetailsImpl.getUserId());
		int delCnt = boardService.deleteBoardLike(parameterMap.getMap());
		return delCnt;
	}

	@PutMapping({ "/like" })
	public Object updateBoardPostLikeCnt(ParameterMap parameterMap) {
		int insCnt = boardService.updateBoardPostLikeCnt(parameterMap.getMap());
		return insCnt;
	}

	// 게시판 카테고리(ADD_BOARD_CATEGORY)
	// 게시판 카테고리 전체 조회
	@GetMapping({ "/category" })
	public List<Map<String, Object>> getBoardCategoryList(ParameterMap parameterMap) {
		String pSearchType = (String) parameterMap.get("pSearchType");
		String pSearchArgv = (String) parameterMap.get("pSearchArgv");
		String pPageNo = (String) parameterMap.get("pPageNo");
		String pPageCount = (String) parameterMap.get("pPageCount");
		pPageCount = "5";

		List<Map<String, Object>> list = boardService.getBoardCategoryList(pSearchType, pSearchArgv, pPageNo,
				pPageCount);
		return list;
	}

	@GetMapping({ "/category/board" })
	public Object getBoardCategory(String boardSeq, ParameterMap parameterMap) {
		String pBoardSeq = (String) parameterMap.get("pBoardSeq");
		String pCategorySeq = (String) parameterMap.get("pCategorySeq");

		Object boardCm = boardService.getBoardCategory(pBoardSeq, pCategorySeq);
		return boardCm;
	}

	@PostMapping({ "/category" })
	public Object insertBoardCategory(ParameterMap parameterMap) {
		int insCnt = boardService.insertBoardCategory(parameterMap.getMap());
		return insCnt;
	}

	@PutMapping({ "/category" })
	public Object updateBoardCategory(ParameterMap parameterMap) {
		int insCnt = boardService.updateBoardCategory(parameterMap.getMap());
		return insCnt;
	}

	@DeleteMapping({ "/category" })
	public Object deleteBoardCategory(String boardSeq, ParameterMap parameterMap) {
		String pBoardSeq = (String) parameterMap.get("pBoardSeq");
		String pCategorySeq = (String) parameterMap.get("pCategorySeq");

		int delCnt = boardService.deleteBoardCategory(pBoardSeq, pCategorySeq);
		return delCnt;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 게시판 그룹 항목(ADD_BOARD_GROUP_ITEM)
	// 게시판 그룹 항목 전체 조회
	@GetMapping({ "/group-item" })
	public List<Map<String, Object>> getBoardGroupItemList(ParameterMap parameterMap) {
		String pSearchType = (String) parameterMap.get("pSearchType");
		String pSearchArgv = (String) parameterMap.get("pSearchArgv");
		String pPageNo = (String) parameterMap.get("pPageNo");
		String pPageCount = (String) parameterMap.get("pPageCount");
		pPageCount = "5";

		List<Map<String, Object>> list = boardService.getBoardGroupItemList(pSearchType, pSearchArgv, pPageNo,
				pPageCount);
		return list;
	}

	@GetMapping({ "/group-item/board" })
	public Object getBoardGroupItem(String boardSeq, ParameterMap parameterMap) {
//		if (boardSeq.isEmpty()) {
//		}

		Object boardGroupItem = boardService.getBoardGroupItem(boardSeq);
		return boardGroupItem;
	}

	@PostMapping({ "/group-item" })
	public Object insertBoardGroupItem(ParameterMap parameterMap) {
		int insCnt = boardService.insertBoardGroupItem(parameterMap.getMap());
		return insCnt;
	}

	@PutMapping({ "/group-item" })
	public Object updateBoardGroupItem(ParameterMap parameterMap) {
		int insCnt = boardService.updateBoardGroupItem(parameterMap.getMap());
		return insCnt;
	}

	@DeleteMapping({ "/group-item" })
	public Object deleteBoardGroupItem(String boardSeq, ParameterMap parameterMap) {

		String pBoardGroupSeq = (String) parameterMap.get("pBoardGroupSeq");
		String pBoardSeq = (String) parameterMap.get("pBoardSeq");

		int delCnt = boardService.deleteBoardGroupItem(pBoardGroupSeq, pBoardSeq);
		return delCnt;
	}

	// 게시판 그룹 항목(ADD_BOARD_GROUP)
	// 게시판 그룹 항목 전체 조회
	@GetMapping({ "/group" })
	public List<Map<String, Object>> getBoardGroupList(ParameterMap parameterMap) {
		String pSearchType = (String) parameterMap.get("pSearchType");
		String pSearchArgv = (String) parameterMap.get("pSearchArgv");
		String pPageNo = (String) parameterMap.get("pPageNo");
		String pPageCount = (String) parameterMap.get("pPageCount");
		pPageCount = "5";

		List<Map<String, Object>> list = boardService.getBoardGroupList(pSearchType, pSearchArgv, pPageNo, pPageCount);
		return list;
	}

	@GetMapping("/group/{boardGroupSeq}")
	public Object getBoardGroup(@PathVariable(value = "boardGroupSeq") String boardGroupSeq,
			ParameterMap parameterMap) {
//		if (boardGroupSeq.isEmpty()) {
//		}

		Object boardGroup = boardService.getBoardGroup(boardGroupSeq);
		return boardGroup;
	}

	@PostMapping({ "/group" })
	public Object insertBoardGroup(ParameterMap parameterMap) {
		int insCnt = boardService.insertBoardGroup(parameterMap.getMap());
		return insCnt;
	}

	@PutMapping({ "/group" })
	public Object updateBoardGroup(ParameterMap parameterMap) {
		int insCnt = boardService.updateBoardGroup(parameterMap.getMap());
		return insCnt;
	}

	@DeleteMapping("/group/{boardGroupSeq}")
	public Object deleteBoardGroup(@PathVariable(value = "boardGroupSeq") String boardGroupSeq,
			ParameterMap parameterMap) {
//		if (boardGroupSeq.isEmpty()) {
//
//		}
		int delCnt = boardService.deleteBoardGroup(boardGroupSeq);
		return delCnt;
	}

	// 게시판 권한 관리(ADD_BOARD_AUTH)
	// 게시판 목록 조회
	@GetMapping({ "/auth" })
	public List<Map<String, Object>> selectBoardAuthList(ParameterMap parameterMap) {
		String pBoardGb = (String) parameterMap.get("pBoardGb");
		String pSearchArgv = (String) parameterMap.get("pSearchArgv");
		String pPageNo = (String) parameterMap.get("pPageNo");
		String pRowCount = (String) parameterMap.get("pRowCount");

		return boardService.selectBoardAuthList(pBoardGb, pSearchArgv, pPageNo, pRowCount);
	}

	// 게시판 권한 관리 - 게시판 구분별 권한 목록 조회
	@GetMapping({ "/auth-gb" })
	public List<Map<String, Object>> selectBoardAuthGbList(ParameterMap parameterMap) {
		String pBoardSeq = (String) parameterMap.get("pBoardSeq");
		String pBoardAuthGb = (String) parameterMap.get("pBoardAuthGb");
		String pPageNo = (String) parameterMap.get("pPageNo");
		String pRowCount = (String) parameterMap.get("pRowCount");

		return boardService.selectBoardAuthGbList(pBoardSeq, pBoardAuthGb, pPageNo, pRowCount);
	}

	// 게시판 권한 관리 - 게시판 구분별 권한 추가
	@PostMapping("/auth-gb")
	public Object insertDaesang(ParameterMap parameterMap) {
		return boardService.insertBoardAuthCd(parameterMap.getMap());
	}

	// 게시판 권한 관리 - 게시판 구분별 권한 삭제
	@DeleteMapping("/auth-gb")
	public Object deleteDaesang(ParameterMap parameterMap) {
		String pBoardSeq = (String) parameterMap.get("pBoardSeq");
		String pBoardAuthGb = (String) parameterMap.get("pBoardAuthGb");
		String pAuthCd = (String) parameterMap.get("pAuthCd");

		return boardService.deleteBoardAuthCd(pBoardSeq, pBoardAuthGb, pAuthCd);
	}

	// 커뮤니티 - 게시판에 부여된 권한을 조회
	@GetMapping("/auth-gb/{pBoardSeq}")
	public List<Map<String, Object>> selectBoardAuthInfo(@PathVariable(value = "pBoardSeq") String pBoardSeq,
			@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {

		String pUserAuthCd = userDetailsImpl.getDefaultAuthCd();
		String pUserId = userDetailsImpl.getUserId();

		return boardService.selectBoardAuthInfo(pBoardSeq, pUserAuthCd, pUserId);
	}

	@GetMapping("/totSearch")
	public Object totSearch(ParameterMap parameterMap){

		List<Map<String, Object>> list = boardService.totSearch(parameterMap.getMap());
		return list;
	}

}
