package kr.co.codefarm.svcm.systemmanager.menu.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.menuApi";

	
	// 사용자 앱 검색
	public List<Map<String, Object>> getAuthAppList(Map<String, Object> paramMap, String userDefaultAuthCd) {
		paramMap.put("DEFAULT_AUTH_CD", userDefaultAuthCd);
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".authAppList", paramMap);
		return list;
	}
	
	// 메뉴 검색
	public List<Map<String, Object>> getAuthMenuList(Map<String, Object> paramMap, String userDefaultAuthCd) {
		paramMap.put("DEFAULT_AUTH_CD", userDefaultAuthCd);
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".authMenuList", paramMap);
		return list;
	}
	
	// 매뉴얼 검색
	public List<Map<String, Object>> getAppMenuManualList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAppMenuManualList", paramMap);
		return list;
	}
	
}
