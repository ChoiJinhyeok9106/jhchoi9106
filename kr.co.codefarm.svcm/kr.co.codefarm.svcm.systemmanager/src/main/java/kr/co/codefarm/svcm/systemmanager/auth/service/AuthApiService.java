package kr.co.codefarm.svcm.systemmanager.auth.service;


import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.authApi";

	/* 미인증된 사용자(작성자 : 최대건) */
	public List<Map<String, Object>> getNotAuthList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getNotAuthList", paramMap);
		return list;
	}
	
	//권한리스트 불러오기 wjjoo 2022-04-18
	public List<Map<String, Object>> getAllAuthList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAllAuthList", paramMap);
		return list;
	}
	
	// 상담자 조회
	public List<Map<String, Object>> getSangdamsaList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getSangdamsaList", paramMap);
		return list;
	}
	// 배정 권한 조회
	public List<Map<String, Object>> getBaejeongAuthList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getBaejeongAuthList", paramMap);
		return list;
	}
	/* 산업체 -> 인증산업체(작성자 : 최대건) */
	public int updateCompany(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateCompany", pServiceRow);
		return updCnt;
	}
	/* 산업체 권한 처리(작성자 : 최대건) */
	public int updateCompanyAuth(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateCompanyAuth", pServiceRow);
		return updCnt;
	}
	/* 상담사 -> 인증상담사(작성자 : 최대건) */
	public int updateSangdamsa(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateSangdamsa", pServiceRow);
		return updCnt;
	}
	/* 산업체 -> 인증산업체(작성자 : 최대건) */
	public int updateGangsa(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateGangsa", pServiceRow);
		return updCnt;
	}
	/*인증된 비교과 강사*/
	public List<Map<String, Object>> getApprovalGangsaList(Map<String, Object> paramMap) {		
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getApprovalGangsaList", paramMap);
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
	
	/* 사용자의 메뉴별 권한 조회 */
	public List<Map<String, Object>> selectUserAuth(Map<String, Object> paramMap) {	
	    List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectUserAuth", paramMap);
	    return list;
    }
}
