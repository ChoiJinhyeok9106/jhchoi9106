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
public class AppApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.appApi";

	public List<Map<String, Object>> getAppList(String pSearchType, String pSearchArgv, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);

		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAppList", paramMap);
		return list;
	}

	public Object getApp(String pAppSeq) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("APP_ID", pAppSeq);
		Object app = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getApp", paramMap);
		return app;
	}

	public List<Map<String, Object>> getSubscribeAppList(String pUserId, String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", pUserId);
		paramMap.put("AUTH_CD", pAuthCd);
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getSubscribeAppList", paramMap);
		return list;
	}
}
