/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.app.service;

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
public class AppService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.app";

	public List<Map<String, Object>> getAppList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAppList", paramMap);
		return list;
	}
	
	public Object getApp(String pAppSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("APP_ID", pAppSeq);
		Object app = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getApp", paramMap);
		return app;
	}
	
	public int deleteApp(String pAppId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("appId", pAppId);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteApp", paramMap);
		return delCnt;
	}
	
	public int updateApp(Map<String, Object> pAppRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateApp", pAppRow);
		return updCnt;
	}
	
	public int insertApp(Map<String, Object> pAppRow) {
		int insertCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertApp", pAppRow);
		return insertCnt;
	}
	
}
