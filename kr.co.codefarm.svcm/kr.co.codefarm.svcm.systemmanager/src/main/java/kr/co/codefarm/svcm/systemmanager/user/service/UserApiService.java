package kr.co.codefarm.svcm.systemmanager.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;

/**
 * @author PHS
 *
 */
@Service
public class UserApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.userApi";

	/* 사용자리스트 조회 */
	public List<Map<String, Object>> getUserList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUserList", paramMap);
		return list;
	}
	

	/* 사용자 조회 */
	public Object getUser(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("USER_ID", pUserId);
		Object app = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getUser", paramMap);
		return app;
	}

	/*사용자 비번 조회*/
	public Object getUserPwd(String userId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", userId);
		Object app = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUserPwd", paramMap);
		return app;
	}

	/*사용자 조회*/
	public int userCheck(Map<String, Object> paramMap) {
		int size = commonDao.selectList(DEFAULT_MAPPER_PATH + ".userCheck", paramMap).size();
		return size;
	}
	

	/* 사용자 구분 조회(작성자 : 최대건) */
	public Object getUserGb() {
		Object app = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUserGb");
		return app;
	}
	
	public Map<String, Object> insertUserApply(Map<String, Object> map) {
		Map<String, Object> retMap = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertUserApply", map);
		return retMap;
	}
	
	public Map<String, Object> insertCorpUserApply(Map<String, Object> map) {
		Map<String, Object> retMap = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertCorpUserApply", map);
		return retMap;
	}
	
	
//	/* 사용자 가입(작성자 : 최대건) */
//	public int insertUser(Map<String, Object> pUserRow) {
//		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUser", pUserRow);
//		return insCnt;
//	}

	/* 사용자 권한가입(작성자 : 최대건) */
	public int insertUserAuth(Map<String, Object> pUserRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUserAuth", pUserRow);
		return insCnt;
	}

	public int insertUserAgreeRes(Map<String, Object> pUserRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUserAgreeRes", pUserRow);
		return insCnt;
	}

	/* 사용자 구분 조회(작성자 : 최대건) */
	public Object getIdDoubleChk(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", pUserId);
		Object list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUserDoubleChk", paramMap);
		return list;
	}
	
	public List<Map<String, Object>> getCorpList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getCorpList", paramMap);
		return list;
	}
	
	public int getCorpDbCheck(Map<String, Object> paramMap) {
		int size = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getCorpDbCheck", paramMap).size();
		return size;
	}


	public Object getNickDoubleChk(Map<String, Object> paramMap) {
		Object list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUserNickDoubleChk", paramMap);
		return list;
	}
	

	/* 사용자 갱신(작성자 : 최대건) */
	public int updateUser(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUser", pServiceRow);
		return updCnt;
	}
	
	/* 사용자 사용여부 갱신(작성자 : 최대건) */
	public int updateuseYn(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUseYn", pServiceRow);
		return updCnt;
	}

	/* 사용자 권한 삭제(작성자 : 최대건) */
	public int deleteUserAuth(Map<String, Object> pUserRow) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteUserAuth", pUserRow);

		return delCnt;
	}

	/* 사용자 이용약관 삭제(작성자 : 최대건) */
	public int deleteUserAgreeRes(Map<String, Object> pUserRow) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteUserAgreeRes", pUserRow);
		return delCnt;
	}

	/* 사용자 아이디 중복 체크 */
	public List<Map<String, Object>> getUserIdChk(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", pUserId);

		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUserIdChk", paramMap);
		return list;
	}

	/* 아이디 찾기 (작성자 : 김두욱) */
	public Object findId(Map<String, Object> paramMap) {
		return commonDao.selectObject(DEFAULT_MAPPER_PATH + ".findId", paramMap);
	}

	/* 비밀번호 찾기 (작성자 : 김두욱) */
	public Object findPw(Map<String, Object> paramMap) {
		return commonDao.selectObject(DEFAULT_MAPPER_PATH + ".findPw", paramMap);
	}
	
	public Object updatePw(Map<String, Object> paramMap) {
		return commonDao.update(DEFAULT_MAPPER_PATH + ".updatePw", paramMap);
	}

	public Object mailAuth(Map<String, Object> paramMap) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".mailAuth", paramMap);
		updCnt += commonDao.update(DEFAULT_MAPPER_PATH + ".mailCorpAuth", paramMap);
		return updCnt;
	}

	/* 비밀번호 변경 (작성자 : 김두욱) */
	public Object changePw(Map<String, Object> paramMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".changePw", paramMap);
	}

	/****************************************************************************************************
	 * 2020-01-28 안소정 추가
	 * 비교과 프로그램 신청(신청) / 나의 비교과 프로그램(재신청)
	 *****************************************************************************************************/
	/* 비교과 프로그램 신청에서 신청 / 나의 비교과 프로그램에서 재신청 시,
	 * 입력한 휴대폰 번호 정보가 사용자 정보와 상이할 경우 사용자 정보 갱신 */
	public int updateUserInfoFromNcuApply(Map<String, Object> pServiceRow) {
		int result = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUserInfoFromNcuApply", pServiceRow);
		return result;
	}

	/* 사용자 타 세션 목록 조회 */
	public List<Map<String, Object>> setUserOtherSession(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".setUserOtherSession", paramMap);
		return list;
	}

	public int getSessionChangeCnt(Map<String, Object> paramMap) {
		return (int) commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getSessionChangeCnt", paramMap);
	}

	public void updateMemberDetailDefaultUse(Map<String, Object> paramMap) {
		commonDao.update(DEFAULT_MAPPER_PATH + ".updateMemberDetailDefaultUse", paramMap);
	}

	public Object insertMemberDetail(Map<String, Object> paramMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".insertMemberDetail", paramMap);
	}

	public int insertUserFromMemberDetail(Map<String, Object> paramMap) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUserFromMemberDetail", paramMap);
		return insCnt;
	}
}
