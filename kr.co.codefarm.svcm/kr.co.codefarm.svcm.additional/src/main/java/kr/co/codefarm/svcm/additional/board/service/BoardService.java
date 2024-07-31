package kr.co.codefarm.svcm.additional.board.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.additional.{databse.main}.board";
	
	
	//게시판(ADD_BOARD)
	//게시판 전체 조회
	public List<Map<String, Object>> getBoardList(String pBoardGb, String pUseYn, String pSearchType, String pSearchArgv, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pBoardGb", pBoardGb);
		paramMap.put("pUseYn", pUseYn);
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectBoardList", paramMap);
		return list;
	}

	public Object getBoard(String pBoardSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BOARD_SEQ", pBoardSeq);
		
		Object board = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectBoard", paramMap);
		return board;
	}

	/*
	public int insertBoard(Map<String, Object> pBoardRow) {
		Map<String, Object> paramMap = new HashMap<>();		
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertBoard", pBoardRow);
		return insCnt;		
	}
	*/
	
	public Map<String, Object> insertBoard(Map<String, Object> pBoardRow) {
		Map<String, Object> insBoardRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertBoard", pBoardRow);
		return insBoardRow;
	}
	
	

	public int updateBoard(Map<String, Object> pBoardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBoard", pBoardRow);
		return updCnt;
	}

	public int deleteBoardMain(String pBoardSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BOARD_SEQ", pBoardSeq);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteBoardMain", paramMap);
		return delCnt;
	}
	
	
	//게시판 그룹(ADD_BOARD_POST)
	//게시판 그룹 전체 조회
	public List<Map<String, Object>> getBoardPostList(Map<String, Object> paramMap) {
		Map<String, Object> boardMap = (Map<String, Object>)getBoard((String) paramMap.get("pBoardSeq"));
		List<Map<String, Object>> list = null;
		
		if(boardMap != null && "01".equals(boardMap.get("SORT_GB"))) {
			// POST_NO 정렬
			list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectBoardPostList", paramMap);
		} else {
			// insert_dttm 정렬
			list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectBoardPostListDate", paramMap);
		}
		
		return list;
	}
	
	public Object getBoardPost(String pPostNo) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("POST_NO", pPostNo);
		
		Object post = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectBoardPost", paramMap);
		return post;
	}
	
	public Object getBoardPostContents(Map<String, Object> pParamMap) {
//		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Map<String, Object> paramMap = new HashMap<>();
		Object post = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectBoardPostContents", pParamMap);
		return post;
	}
	
	public Map<String, Object> insertBoardPost(Map<String, Object> pPostRow) {
		Map<String, Object> insPostRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertBoardPost", pPostRow);
		return insPostRow;
	}
	
	public int updateBoardPost(Map<String, Object> pPostRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBoardPost", pPostRow);
		return updCnt;
	}
	
	public int updateBoardPostCnt(Map<String, Object> pPostRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBoardPostCnt", pPostRow);
		return updCnt;
	}

	public int updateBoardPostCmCnt(Map<String, Object> pPostRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBoardPostCmCnt", pPostRow);
		return updCnt;
	}
	/*
	public int deleteBoardPost(String pPostNo) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("POST_NO", pPostNo);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteBoardPost", paramMap);
		return delCnt;
	}
	*/
	public int deleteBoardPost(String pPostNo) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("POST_NO", pPostNo);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteBoardPost", paramMap);
		return delCnt;
	}
	
	/*
	public int removeBoardPost(String pPostNo) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("POST_NO", pPostNo);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".removeBoardPost", paramMap);
		return delCnt;
	}
	*/
	public int removeBoardPost(String pPostNo) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("POST_NO", pPostNo);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".removeBoardPost", paramMap);
		return delCnt;
	}
		
		
		
	//게시판 댓글(ADD_BOARD_CM)
	//게시판 댓글 전체 조회
	public List<Map<String, Object>> getBoardCmList(String pPostNo, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("pPostNo", pPostNo);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);

		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectPostCmList", paramMap);
		return list;
	}


	public int insertBoardCm(Map<String, Object> pPostCmRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertPostCm", pPostCmRow);
		return insCnt;
	}

	public int updateBoardCm(Map<String, Object> pPostCmRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updatePostCm", pPostCmRow);
		return updCnt;
	}

	public int deleteBoardCm(String pPostNo, String pCmSeq) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("POST_NO", pPostNo);
		paramMap.put("CM_SEQ", pCmSeq);

		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deletePostCm", paramMap);
		return delCnt;
	}

	public int removeBoardCm(String pPostNo, String pCmSeq) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("POST_NO", pPostNo);
		paramMap.put("CM_SEQ", pCmSeq);

		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".removePostCm", paramMap);
		return delCnt;
	}

	//QnA 답변 조회
	public List<Map<String, Object>> answerPostList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".answerPostList", paramMap);
		return list;
	}

	//게시판 좋아요(ADD_POST_LIKE)
	//좋아요 체크
	public List<Map<String, Object>> checkPostLike(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".checkPostLike", paramMap);
		return list;
	}

	//좋아요 등록
	public int insertBoardLike(Map<String, Object> paramMap) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertPostLike", paramMap);
		return insCnt;
	}

	// 좋아요 취소
	public int deleteBoardLike(Map<String, Object> paramMap) {
		return commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteBoardLike", paramMap);
	}

	// 좋아요 카운트 업데이트
	public int updateBoardPostLikeCnt(Map<String, Object> pPostRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBoardPostLikeCnt", pPostRow);
		return updCnt;
	}
		
		
		
		
		
	
	//게시판 카테고리(ADD_BOARD_CATEGORY)
	//게시판 카테고리 전체 조회
	public List<Map<String, Object>> getBoardCategoryList(String pSearchType, String pSearchArgv, String pPageNo, String pPageCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pPageCount", pPageCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectBoardCategoryList", paramMap);
		return list;
	}

	public Object getBoardCategory(String pBoardSeq, String pCategorySeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BOARD_SEQ", pBoardSeq);
		paramMap.put("CATEGORY_SEQ", pCategorySeq);
		
		Object common = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectBoardCategory", paramMap);
		return common;
	}

	public int insertBoardCategory(Map<String, Object> pBoardCategoryRow) {
		Map<String, Object> paramMap = new HashMap<>();
		
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertBoardCategory", pBoardCategoryRow);
		return insCnt;
	}

	public int updateBoardCategory(Map<String, Object> pBoardCategoryRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBoardCategory", pBoardCategoryRow);
		return updCnt;
	}

	public int deleteBoardCategory(String pBoardSeq, String pCategorySeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BOARD_SEQ", pBoardSeq);
		paramMap.put("CATEGORY_SEQ", pCategorySeq);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteBoardCategory", paramMap);
		return delCnt;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//게시판 그룹 항목(ADD_BOARD_GROUP_ITEM)
	//게시판 그룹 항목 전체 조회
	public List<Map<String, Object>> getBoardGroupItemList(String pSearchType, String pSearchArgv, String pPageNo,
			String pPageCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pPageCount", pPageCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectBoardGroupItemList", paramMap);
		return list;
	}

	public Object getBoardGroupItem(String boardSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertBoardGroupItem(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateBoardGroupItem(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteBoardGroupItem(String pBoardGroupSeq, String pBoardSeq) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	//게시판 그룹 항목(ADD_BOARD_GROUP)
	//게시판 그룹 항목 전체 조회
	public List<Map<String, Object>> getBoardGroupList(String pSearchType, String pSearchArgv, String pPageNo,
			String pPageCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getBoardGroup(String boardGroupSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertBoardGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateBoardGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteBoardGroup(String boardGroupSeq) {
		// TODO Auto-generated method stub
		return 0;
	}

	//게시판 권한 관리(ADD_BOARD_AUTH)
	//게시판 목록 조회
	public List<Map<String, Object>> selectBoardAuthList(String pBoardGb, String pSearchArgv, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pBoardGb", pBoardGb);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectBoardAuthList", paramMap);
	}


	// 게시판 권한 관리(ADD_BOARD_AUTH)
	// 게시판 권한 관리 - 게시판 구분별 권한 목록 조회
	public List<Map<String, Object>> selectBoardAuthGbList(String pBoardSeq, String pBoardAuthGb, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pBoardSeq", pBoardSeq);
		paramMap.put("pBoardAuthGb", pBoardAuthGb);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectBoardAuthGbList", paramMap);
	}

	// 게시판 권한 관리 - 게시판 구분별 권한 추가
	public int insertBoardAuthCd(Map<String, Object> pRow) {
		return commonDao.insert(DEFAULT_MAPPER_PATH + ".insertBoardAuthCd", pRow);
	}

	// 게시판 권한 관리 - 게시판 구분별 권한 삭제
	public int deleteBoardAuthCd(String pBoardSeq, String pBoardAuthGb, String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pBoardSeq", pBoardSeq);
		paramMap.put("pBoardAuthGb", pBoardAuthGb);
		paramMap.put("pAuthCd", pAuthCd);
		
		return commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteBoardAuthCd", paramMap);
	}

	// 커뮤니티 - 게시판에 부여된 권한을 조회
	public List<Map<String, Object>> selectBoardAuthInfo(String boardSeq, String userAuthCd, String userId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pBoardSeq", boardSeq);
		paramMap.put("pUserAuthCd", userAuthCd);
		paramMap.put("pUserId", userId);
		
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectBoardAuthInfo", paramMap);
	}

	//통합검색
	public List<Map<String, Object>> totSearch(Map<String, Object> paramMap) {

		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".totSearch", paramMap);
		return list;
	}
}
