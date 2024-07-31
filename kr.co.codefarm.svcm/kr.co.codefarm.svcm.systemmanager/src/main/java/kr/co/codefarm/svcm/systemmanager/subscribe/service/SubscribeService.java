package kr.co.codefarm.svcm.systemmanager.subscribe.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;

@Service
public class SubscribeService {
	
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.subscribe";
	
	public List<Map<String, Object>> getSubscribeList(Map<String, Object> paramMap) {
		List<Map<String, Object>> retMap = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getSubscribeList", paramMap);
		for(int i = 0 ; i < retMap.size() ; i++){
			retMap.get(i).put("APP_LIST", commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAppList", retMap.get(i)));
		}
		return retMap;
	}

	public int subscription(Map<String, Object> paramMap) {
		return commonDao.insert(DEFAULT_MAPPER_PATH + ".subscription", paramMap);
	}

	public int cancelSubscription(Map<String, Object> paramMap) {
		return commonDao.delete(DEFAULT_MAPPER_PATH + ".cancelSubscription", paramMap);
	}

	public List<Map<String, Object>> getSubscriptionList(Map<String, Object> paramMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getSubscriptionList", paramMap);
	}

	public int acceptService(Map<String, Object> paramMap) {
		return commonDao.insert(DEFAULT_MAPPER_PATH + ".acceptService", paramMap);
	}

	public int rejectService(Map<String, Object> paramMap) {
		return commonDao.delete(DEFAULT_MAPPER_PATH + ".rejectService", paramMap);
	}
	
	public List<Map<String, Object>> getMySubscribeList(Map<String, Object> paramMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getMySubscribeList", paramMap);
	}
	
}
