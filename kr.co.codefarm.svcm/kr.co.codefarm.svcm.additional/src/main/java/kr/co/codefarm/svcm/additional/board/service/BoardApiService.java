package kr.co.codefarm.svcm.additional.board.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.additional.{databse.main}.boardApi";
	
	//게시판(ADD_BOARD)
	// 위젯 - 일반, 탭, 일반이미지, 4컷이미지 조회
	public List<Map<String, Object>> getWidgetList(String pBoardSeq, String pSearchCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pBoardSeq", pBoardSeq);
		paramMap.put("pSearchCount", pSearchCount);
		
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getWidgetList", paramMap);
	}

	public List<Map<String, Object>> getNoticeList(String confYn,String pSearchCount) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("pSearchCount", pSearchCount);
		paramMap.put("confYn", confYn);

		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getNoticeList", paramMap);
	}

	public List<Map<String, Object>> getMarketList(String pUserId ,String pSearchCount) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("pSearchCount", pSearchCount);
		paramMap.put("USER_ID", pUserId);

		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getMarketList", paramMap);
	}

	public List<Map<String, Object>> getActiveList(String pUserId ,String pSearchCount) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("pSearchCount", pSearchCount);
		paramMap.put("USER_ID", pUserId);

		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getActiveList", paramMap);
	}

	public List<Map<String, Object>> getConfList(String pUserId ,String pSearchCount) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("pSearchCount", pSearchCount);
		paramMap.put("USER_ID", pUserId);

		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getConfList", paramMap);
	}

	// SWC - 메인화면 게시판 조회
	public List<Map<String, Object>> getSWCMainBoardList(Map<String, Object> paramMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getSWCMainBoardList", paramMap);
	}
	
	// SWC - 커뮤니티 조회
	public List<Map<String, Object>> getSWCBoardList(Map<String, Object> paramMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getSWCBoardList", paramMap);
	}

	public Object getBoardInfo(String pBoardSeq) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("BOARD_SEQ", pBoardSeq);

		Object board = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getBoardInfo", paramMap);
		return board;
	}
	
	// 커뮤니티 - 게시판에 부여된 권한을 조회
	public List<Map<String, Object>> getBoardAuthInfo(String boardSeq, String userAuthCd, String userId, String userAuthNm) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pBoardSeq", boardSeq);
		paramMap.put("pUserAuthCd", userAuthCd);
		paramMap.put("pUserId", userId);
		paramMap.put("pUserAuthNm", userAuthNm);
		
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getBoardAuthInfo", paramMap);
	}
	
	public List<Map<String, Object>> getAddFileList(Map<String, Object> paramMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAddFileList", paramMap);
	}

	public List<Map<String, Object>> getPostList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getPostList", paramMap);
		return list;
	}

	public Object getPost(Map<String, Object> paramMap) {
		return commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getPost", paramMap);
	}

	public Map<String, Object> insertPost(Map<String, Object> pPostRow) {
		Map<String, Object> insPostRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertPost", pPostRow);
		return insPostRow;
	}

	public int updatePost(Map<String, Object> pPostRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updatePost", pPostRow);
		return updCnt;
	}

	public int deletePost(String pPostNo) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("POST_NO", pPostNo);

		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deletePost", paramMap);
		return delCnt;
	}

	public List<Map<String, Object>> getPostCm(String pPostNo, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("pPostNo", pPostNo);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);

		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getPostCm", paramMap);
		return list;
	}

	public int insertPostCm(Map<String, Object> pPostCmRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertCm", pPostCmRow);
		return insCnt;
	}

	public int deletePostCm(String pPostNo, String pCmSeq) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("POST_NO", pPostNo);
		paramMap.put("CM_SEQ", pCmSeq);

		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteCm", paramMap);
		return delCnt;
	}

	public int updatePostCmCnt(Map<String, Object> pPostRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updatePostCmCnt", pPostRow);
		return updCnt;
	}

	//게시판 좋아요(ADD_POST_LIKE)
	//좋아요 체크
	public List<Map<String, Object>> checkLike(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".checkLike", paramMap);
		return list;
	}

	//좋아요 등록
	public int postLike(Map<String, Object> paramMap) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".postLike", paramMap);
		return insCnt;
	}

	// 좋아요 취소
	public int postLikeCancel(Map<String, Object> paramMap) {
		return commonDao.delete(DEFAULT_MAPPER_PATH + ".postLikeCancel", paramMap);
	}

	// 좋아요 카운트 업데이트
	public int updatePostLikeCnt(Map<String, Object> pPostRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updatePostLikeCnt", pPostRow);
		return updCnt;
	}

	public Object getPostAns(Map<String, Object> paramMap) {
		return commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getPostAns", paramMap);
	}

	public int updatePostCnt(Map<String, Object> pPostRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updatePostCnt", pPostRow);
		return updCnt;
	}

	
	public List<Map<String, Object>> getAllNoticeBoard(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAllNoticeBoard", paramMap);
		return list;
	}


}
