/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.user.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@Service
public class UserService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.user";
	
	/* 사용자 조회 */
	public List<Map<String, Object>> getUserList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUserList", paramMap);
		return list;
	}
	
	/* 사용자 조회 */
	public List<Map<String, Object>> getCorpUserList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getCorpUserList", paramMap);
		return list;
	}
	
	/* 사용자 조회 */
	public List<Map<String, Object>> getUserSignList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUserSignList", paramMap);
		return list;
	}

	/* 사용자 조회 */
	public List<Map<String, Object>> getUserCorpSignList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUserCorpSignList", paramMap);
		return list;
	}
	
	/* 사용자 조회 */
	public Object getUser(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("USER_ID", pUserId);
		Object app = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getUser", paramMap);
		return app;
	}

	/* 사용자 조회 */
	public Object getUserSync(Map<String, Object> paramMap) {
		Object app = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getUserSync", paramMap);
		return app;
	}
	
	/* 사용자 조회 */
	public String getUserPwd(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		String pwd = (String) commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getUserPwd", paramMap);
		return pwd;
	}
	
	/* 사용자 아이디 중복 체크 */
	public List<Map<String, Object>> getUserIdChk(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", pUserId);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUserIdChk", paramMap);
		return list;
	}
	
	/* 가입승인 */
	public int userSignAdmission(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".userSignAdmission", pServiceRow);
		return updCnt;
	}
	
	/* 가입승인 */
	public int userCorpSignAdmission(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".userCorpSignAdmission", pServiceRow);
		return updCnt;
	}
	
	/* 가입반려 */
	public int rejectUser(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".rejectUser", pServiceRow);
		return updCnt;
	}
	
	/* 가입반려 */
	public int rejectCorpUser(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".rejectCorpUser", pServiceRow);
		return updCnt;
	}
	
	/* 사용자 갱신 */
	public int updateUser(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUser", pServiceRow);
		return updCnt;
	}

	/* 사용자 갱신 PIC */
	public int updateUserPic(Map<String, Object> pUsereRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUserPic", pUsereRow);
		return updCnt;
	}

	/* 사용자 마이페이지 정보 변경 및 적용 */
	public int updateUserInfo(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUser", pServiceRow);
		return updCnt;
	}

	/* 사용자 갱신 PWD */
	public int updateUserPwd(Map<String, Object> pUsereRow) {
		//TODO: 차 후 프로시저로 대체
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUserPwd", pUsereRow);
		return updCnt;
	}
	
	
	
	/* 사용자 삽입 */
	public int insertUser(Map<String, Object> pUserRow) {
		Map<String, Object> paramMap = new HashMap<>();
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUser", pUserRow);
		return insCnt;
	}
	
	/* 사용자 삭제 */
	public int deleteUser(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", pUserId);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteUser", paramMap);
		return delCnt;
	}
	
	public Object getUserSort(String pUserId, String pSortGb) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		paramMap.put("SORT_GB", pSortGb);
		Object app = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getUserSort", paramMap);
		return app;
	}
	
	public int deleteUserSort(String pUserId, String pSortGb) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("USER_ID", pUserId);
		paramMap.put("SORT_GB", pSortGb);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteUserSort", paramMap);
		return delCnt;
	}
	
	public int updateUserSort(String pUserId, String pSortGb, Map<String, Object> pAppRow) {
		pAppRow.put("USER_ID", pUserId);
		pAppRow.put("SORT_GB", pSortGb);
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUserSort", pAppRow);
		return updCnt;
	}
	
	public int insertUserSort(String pUserId, String pSortGb, Map<String, Object> pAppRow) {
		pAppRow.put("USER_ID", pUserId);
		pAppRow.put("SORT_GB", pSortGb);
		
		deleteUserSort(pUserId, pSortGb);
		int insAppRow = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUserSort", pAppRow);
		return insAppRow;
	}
	
	public Object getUserAccount(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		Object app = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getUserAccount", paramMap);
		return app;
	}
	
	public void changeUserPwd(Map<String, Object> map) {
		commonDao.selectList(DEFAULT_MAPPER_PATH + ".changePw", map);
	}
	
	public void changeUserPwdInit(Map<String, Object> map) {
		commonDao.selectList(DEFAULT_MAPPER_PATH + ".changePasswordKren", map);
	}
	
	public int checkedIdPw(String pUserId, String pwd) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", pUserId);
		paramMap.put("USER_PW", pwd);
		
		return (int)commonDao.selectObject(DEFAULT_MAPPER_PATH + ".checkedIdPw", paramMap);
	}
	
	/* 사용자 동기화(추가 ) */
	public void userAdd() {
		commonDao.selectList(DEFAULT_MAPPER_PATH + ".SP_SMN_USER_V_INSERT");
	}

	/* 기업회원 조회 */
	public List<Map<String, Object>> userEnterprise(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".userEnterprise", paramMap);
		return list;
	}

	/* 표시여부 변경 */
	public int enterpriseUseYnToggle(Map<String, Object> map) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".enterpriseUseYnToggle", map);
		return updCnt;
	}

	/* 표시여부 변경 */
	public int entChange(Map<String, Object> map) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".entChange", map);
		return updCnt;
	}

	/* 표시여부 변경 */
	public int orgChange(Map<String, Object> map) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".orgChange", map);
		return updCnt;
	}

	public int menuOpenYn(Map<String, Object> map) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".menuOpenYn", map);
		return updCnt;
	}

	public Map<String, Object> applyUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
