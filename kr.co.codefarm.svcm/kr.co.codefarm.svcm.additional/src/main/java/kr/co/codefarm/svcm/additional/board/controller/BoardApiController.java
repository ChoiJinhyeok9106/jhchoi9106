/**
 * 
 */
package kr.co.codefarm.svcm.additional.board.controller;

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

import kr.co.codefarm.svcm.additional.board.service.BoardApiService;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.service.mail.EmailService;
import kr.co.codefarm.svcm.systemmanager.user.service.UserApiService;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/api/additional/board")
public class BoardApiController {
	
	@Autowired
	private BoardApiService boardApiService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserApiService userApiService;

	// 외부사용자용 게시판(ADD_BOARD)
	// 위젯 - 일반, 탭, 일반이미지, 4컷이미지 조회
	@GetMapping("/widget/{boardSeq}")
	public List<Map<String, Object>> getWidgetList(@PathVariable(value="boardSeq") String pBoardSeq, ParameterMap parameterMap) {
		String pSearchCount = (String) parameterMap.get("pSearchCount");
		return boardApiService.getWidgetList(pBoardSeq, pSearchCount);
	}

	@GetMapping("/widget/notice")
	public List<Map<String, Object>> getNoticeList( ParameterMap parameterMap) {
		String pSearchCount = (String) parameterMap.get("pSearchCount");
		String confYn = (String) parameterMap.get("confYn");
		return boardApiService.getNoticeList(confYn,pSearchCount);

	}

	@GetMapping("/widget/market")
	public List<Map<String, Object>> getMarketList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		String pSearchCount = (String) parameterMap.get("pSearchCount");

		String pUserId = (String) userDetailsImpl.getUserId();

		return boardApiService.getMarketList(pUserId,pSearchCount);
	}

	@GetMapping("/widget/active")
	public List<Map<String, Object>> getActiveList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		String pSearchCount = (String) parameterMap.get("pSearchCount");

		String pUserId = (String) userDetailsImpl.getUserId();

		return boardApiService.getActiveList(pUserId,pSearchCount);
	}

	@GetMapping("/widget/conf")
	public List<Map<String, Object>> getConfList(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		String pSearchCount = (String) parameterMap.get("pSearchCount");

		String pUserId = (String) userDetailsImpl.getUserId();

		return boardApiService.getConfList(pUserId,pSearchCount);
	}

	@GetMapping("/{boardSeq}")
	public Object getBoardInfo(@PathVariable(value = "boardSeq") String boardSeq, ParameterMap parameterMap) {
//		if (boardSeq.isEmpty()) {
//		}

		Object board = boardApiService.getBoardInfo(boardSeq);
		return board;
	}
	
	// 커뮤니티 - 게시판에 부여된 권한을 조회
	@GetMapping("/auth-gb/{pBoardSeq}")
	public List<Map<String, Object>> getBoardAuthInfo(@PathVariable(value = "pBoardSeq") String pBoardSeq,
			@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap) {
		
		String pUserAuthCd = userDetailsImpl.getDefaultAuthCd();
		String pUserAuthNm = userDetailsImpl.getDefaultAuthNm();
		String pUserId = userDetailsImpl.getUserId();

		return boardApiService.getBoardAuthInfo(pBoardSeq, pUserAuthCd, pUserId, pUserAuthNm);
	}

	@GetMapping("/list")
	public List<Map<String, Object>> getPostList(ParameterMap parameterMap){

		List<Map<String, Object>> list = boardApiService.getPostList(parameterMap.getMap());
		return list;
	}

	@GetMapping("/post")
	public Object getPost(ParameterMap parameterMap){
		return boardApiService.getPost(parameterMap.getMap());
	}

	@PostMapping({ "/post" })
	public Object insertPost(ParameterMap parameterMap) {
		Map<String, Object> insObj = boardApiService.insertPost(parameterMap.getMap());
		return insObj;
	}

	@PutMapping({ "/post" })
	public Object updatePost(HttpServletRequest request, ParameterMap parameterMap) {
		

		int insCnt = boardApiService.updatePost(parameterMap.getMap());
		return insCnt;
	}

	@DeleteMapping("/post")
	public Object deletePost(String postNo, ParameterMap parameterMap) {
		String pPostNo = (String) parameterMap.get("pPostNo");

		int delCnt = boardApiService.deletePost(pPostNo);
		return delCnt;
	}

	@GetMapping("/post/cm")
	public List<Map<String, Object>> getPostCm(ParameterMap parameterMap){
		String pPostNo = (String) parameterMap.get("pPostNo");
		String pPageNo = (String) parameterMap.get("pPageNo");
		String pRowCount = (String) parameterMap.get("pRowCount");
		// pPageCount = "5";

		List<Map<String, Object>> list = boardApiService.getPostCm(pPostNo, pPageNo, pRowCount);
		return list;
	}

	@PostMapping({ "/post/cm" })
	public Object insertPostCm(ParameterMap parameterMap) {
		int insCnt = boardApiService.insertPostCm(parameterMap.getMap());
		return insCnt;
	}

	@DeleteMapping({ "/post/cm" })
	public Object deletePostCm(String boardSeq, ParameterMap parameterMap) {

		String pPostNo = (String) parameterMap.get("pPostNo");
		String pCmSeq = (String) parameterMap.get("pCmSeq");

		int delCnt = boardApiService.deletePostCm(pPostNo, pCmSeq);
		return delCnt;
	}

	@PutMapping({ "/post/cm/count" })
	public Object updateBoardPostCmCnt(ParameterMap parameterMap) {
		int insCnt = boardApiService.updatePostCmCnt(parameterMap.getMap());
		return insCnt;
	}

	// 게시판 좋아요(ADD_POST_LIKE)
	// 게시판 좋아요 중복 체크
	@GetMapping({ "/post/like" })
	public List<Map<String, Object>> checkLike(ParameterMap parameterMap) {
		List<Map<String, Object>> list = boardApiService.checkLike(parameterMap.getMap());
		return list;
	}

	// 게시판 좋아요 등록
	@PostMapping({ "/post/like" })
	public Object postLike(ParameterMap parameterMap) {
		int insCnt = boardApiService.postLike(parameterMap.getMap());
		return insCnt;
	}

	// 게시판 좋아요 취소
	@DeleteMapping("/post/like")
	public Object postLikeCancel(@AuthenticationPrincipal ScvmUserDetailsImpl userDetailsImpl, ParameterMap parameterMap){
		parameterMap.put("USER_ID",  userDetailsImpl.getUserId());
		int delCnt = boardApiService.postLikeCancel(parameterMap.getMap());
		return delCnt;
	}

	@PutMapping({ "/post/like" })
	public Object updatePostLikeCnt(ParameterMap parameterMap) {
		int insCnt = boardApiService.updatePostLikeCnt(parameterMap.getMap());
		return insCnt;
	}

	@GetMapping("/post/answer")
	public Object getPostAns(ParameterMap parameterMap){
		return boardApiService.getPostAns(parameterMap.getMap());
	}

	@PutMapping({ "/post/count" })
	public Object updatePostCnt(ParameterMap parameterMap) {
		int insCnt = boardApiService.updatePostCnt(parameterMap.getMap());
		return insCnt;
	}
	
	@GetMapping("/all/notice")
	public List<Map<String, Object>> getAllNoticeBoard(ParameterMap parameterMap){
		List<Map<String, Object>> list = boardApiService.getAllNoticeBoard(parameterMap.getMap());
		return list;
	}

}


