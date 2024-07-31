package kr.co.codefarm.svcm.systemmanager.auth.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.auth";

	public List<Map<String, Object>> getAllAuthList() {		
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectAllAuthList");
	}
	
	//Auth Service
	public List<Map<String, Object>> getAuthList(String pUseYn, String pAuthGb, String pSearchType, String pSearchArgv, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pUseYn", pUseYn);
		paramMap.put("pAuthGb", pAuthGb);
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectAuthList", paramMap);
		return list;
	}

	//wjjoo 2022.03.15 추가
	public List<Map<String, Object>> getSubAuthList(Map<String, Object> paramMap) {		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectSubAuthList", paramMap);
		return list;
	}
	public int getRoleCheck(String userId,String authCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", userId);
		paramMap.put("AUTH_CD", authCd);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectAuthCount", paramMap);
		int cnt = Integer.parseInt(list.get(0).get("CNT").toString());
		return cnt;
	}
	
	public Object getAuth(String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("AUTH_CD", pAuthCd);
		
		Object common = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectAuth", paramMap);
		return common;
	}

	public int insertAuth(Map<String, Object> pAuthRow) {
		Map<String, Object> paramMap = new HashMap<>();
		
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAuth", pAuthRow);
		return insCnt;
	}
	
	public Object getAuthSeq(Map<String, Object> paramMap) {
		Object returnSeq = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getAuthSeq", paramMap);
		return returnSeq;
	}
	
	public Object getSortSeq(Map<String, Object> paramMap) {
		Object returnSeq = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getSortSeq", paramMap);
		return returnSeq;
	}
	
	
	public int updateAuth(Map<String, Object> pAuthRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAuth", pAuthRow);
		return updCnt;
	}
	
	
	public int deleteAuth(String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("AUTH_CD", pAuthCd);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteAuth", paramMap);
		return delCnt;
	}
	
	
	
	//App Auth Service
	public List<Map<String, Object>> getAppAuthList(String pAuthCd, String pAppId, String pSearchType, String pSearchArgv, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("AUTH_CD", pAuthCd);
		paramMap.put("APP_ID", pAppId);
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectAppAuthList", paramMap);
		return list;
	}

	public List<Map<String, Object>> getAppAllAuthList(String pAppId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("APP_ID", pAppId);

		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectAppAllAuthList", paramMap);
		return list;
	}
	
	public Object getAppAuth(String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("AUTH_CD", pAuthCd);
		
		Object common = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectAppAuth", paramMap);
		return common;
	}

	public int insertAppAuth(Map<String, Object> pAppAuthRow) {
		Map<String, Object> paramMap = new HashMap<>();
		
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAppAuth", pAppAuthRow);
		return insCnt;
	}
	
	public int updateAppAuth(Map<String, Object> pAppAuthRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAppAuth", pAppAuthRow);
		return updCnt;
	}
	
	
	public int deleteAppAuth(String pAppId, String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("APP_ID", pAppId);
		paramMap.put("AUTH_CD", pAuthCd);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteAppAuth", paramMap);
		return delCnt;
	}

	

	
	//App Auth Service
	public List<Map<String, Object>> getUserAddAuthList(String pAuthCd, String pUserId, String pSearchType, String pSearchArgv, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("AUTH_CD", pAuthCd);
		paramMap.put("USER_ID", pUserId);
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectUserAddAuthList", paramMap);
		return list;
	}
	
	public Object getUserAddAuth(String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("AUTH_CD", pAuthCd);
		
		Object common = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectUserAddAuth", paramMap);
		return common;
	}

	public int insertUserAddAuth(Map<String, Object> pUserAddAuthRow) {
		Map<String, Object> paramMap = new HashMap<>();
		
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUserAddAuth", pUserAddAuthRow);
		return insCnt;
	}
	
	public int updateUserAddAuth(Map<String, Object> pUserAddAuthRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUserAddAuth", pUserAddAuthRow);
		return updCnt;
	}
	
	
	public int deleteUserAddAuth(String pUserId, String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		paramMap.put("AUTH_CD", pAuthCd);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteUserAddAuth", paramMap);
		return delCnt;
	}
	
	//App Auth Service
	public List<Map<String, Object>> getUserAddDaesangList(String pAuthCd, String pUserGroupGb, String pUserStatusGb, String pSearchArgv, String pSearchDeptNm) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("AUTH_CD", pAuthCd);
		paramMap.put("USER_GROUP_GB", pUserGroupGb);
		paramMap.put("USER_STATUS_GB", pUserStatusGb);
		paramMap.put("USER_NM", pSearchArgv);
		paramMap.put("DEPT_NM", pSearchDeptNm);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectUserAddDaesangList", paramMap);
		return list;
	}
	
	//App Auth Service
	public List<Map<String, Object>> getUserAddDaesangOrgList(String pAuthCd, String pUserGroupGb, String pUserStatusGb, String pSearchArgv, String pDept) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("AUTH_CD", pAuthCd);
		paramMap.put("USER_GROUP_GB", pUserGroupGb);
		paramMap.put("USER_STATUS_GB", pUserStatusGb);
		paramMap.put("USER_NM", pSearchArgv);
		paramMap.put("ORG_CD", pDept);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectUserAddDaesangOrgList", paramMap);
		return list;
	}
	
	public int insertWidgetAuth(Map<String, Object> pWidgetAuthRow) {
		Map<String, Object> paramMap = new HashMap<>();
		
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertWidgetAuth", pWidgetAuthRow);
		return insCnt;
	}
	
	public int deleteWidgetAuth(String pAppId, String pWidgetSeq, String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("APP_ID", pAppId);
		paramMap.put("WIDGET_SEQ", pWidgetSeq);
		paramMap.put("AUTH_CD", pAuthCd);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteWidgetAuth", paramMap);
		return delCnt;
	}
	
	/**
	 * IP접근제한관리
	 */
	/* 접근허용 IP 리스트 조회 */
	public List<Map<String, Object>> selectIpRestrictedAccessMngList(Map<String, Object> paramMap) {	
	    List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectIpRestrictedAccessMngList", paramMap);
	    return list;
    }
	
	/* 접근허용 IP 삽입 */
	public int insertMileageoperationmng(Map<String, Object> pRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertIpRestrictedAccessMngList", pRow);
		return insCnt;
	}
	
	/* 접근허용 IP 삭제 */
	public int deleteMileageoperationmng(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteIpRestrictedAccessMngList", paramMap);
		return delCnt;
	}
	
	public int menuAuthCheck(String pAppId, String pMenuCd, String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("APP_ID", pAppId);
		paramMap.put("MENU_CD", pMenuCd);
		paramMap.put("USER_ID", pUserId);
		
		return (int) commonDao.selectObject(DEFAULT_MAPPER_PATH + ".menuAuthCheck", paramMap);
	}
}
