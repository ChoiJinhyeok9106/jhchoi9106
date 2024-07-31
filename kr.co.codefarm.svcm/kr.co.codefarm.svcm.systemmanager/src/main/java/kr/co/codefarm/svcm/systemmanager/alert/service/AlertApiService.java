package kr.co.codefarm.svcm.systemmanager.alert.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlertApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.alertApi";
	
	//Alert Service
	public List<Map<String, Object>> getUserUnidentifiedAlarmDataList(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", pUserId);
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectUserUnidentifiedAlarmDataList", paramMap);
		return list;
	}
	
	//Alert Service
	public int getUnidentifiedAlarmDataCnt(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", pUserId);
		int cnt = (int) commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getUnidentifiedAlarmDataCnt", paramMap);
		return cnt;
	}
}
