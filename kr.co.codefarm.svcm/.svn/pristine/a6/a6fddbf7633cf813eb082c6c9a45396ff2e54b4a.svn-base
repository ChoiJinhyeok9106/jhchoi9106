/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.defaultinfo.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@Service
public class DefaultInfoApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.defaultinfoApi";
	
	/* 이용약관(작성자 : 최대건) */
	public List<Map<String, Object>> getDefaultinfo() {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getDefaultinfo");
		return list;
	}

	/* 기본정보조회 */
	public List<Map<String, Object>> getInfo(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getInfo", paramMap);
		return list;
	}

	
	public List<Map<String, Object>> getTerms(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getTerms", paramMap);
		return list;
	}
	
	public List<Map<String, Object>> getLastTerms(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getLastTerms", paramMap);
		return list;
	}
	
}
