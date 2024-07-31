package kr.co.codefarm.svcm.additional.calendar.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalendarService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.additional.{databse.main}.calendar";
	
	
	//캘린더 환경(ADD_CAL_ENV)
	//캘린더 환경 전체 조회
	public List<Map<String, Object>> getCalEnvList(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectCalEnvList", paramMap);
		return list;
	}

	public Object getCalEnv(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		
		Object calEnv = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectCalEnv", paramMap);
		return calEnv;
	}


	
	public int insertCalEnv(Map<String, Object> pCalEnvRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertCalEnv", pCalEnvRow);
		return insCnt;
	}
	


	public int updateCalEnv(Map<String, Object> pCalEnvRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateCalEnv", pCalEnvRow);
		return updCnt;
	}

	public int deleteCalEnv(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteCalEnv", paramMap);
		return delCnt;
	}
	
	
	//캘린더 환경(ADD_CAL)
	//캘린더 전체 조회
	public List<Map<String, Object>> getCalendarList(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
					
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectCalendarList", paramMap);
		return list;
	}
	
	public Object getCalendar(String pUserId, String pCalSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		paramMap.put("CAL_SEQ", pCalSeq);
		
		Object cal = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectCalendar", paramMap);
		return cal;
	}
	
	public Map<String, Object> insertCalendar(Map<String, Object> pCalRow) {
		Map<String, Object> insCalRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertCalendar", pCalRow);
		return insCalRow;
	}
	
	public int updateCalendar(Map<String, Object> pCalRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateCalendar", pCalRow);
		return updCnt;
	}
	

	public int deleteCalendar(String pUserId, String pCalSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		paramMap.put("CAL_SEQ", pCalSeq);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteCalendar", paramMap);
		return delCnt;
	}
	
		
	//캘린더 환경(ADD_CAL_ENV)
	//캘린더 환경 전체 조회
	public List<Map<String, Object>> getCalEventList(String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
					
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectCalEventList", paramMap);
		return list;
	}
	
	public List<Map<String, Object>> getCalEvent(String pUserId, String pIljeongSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		paramMap.put("ILJEONG_SEQ", pIljeongSeq);
					
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectCalEventList", paramMap);
		return list;
	}
	
	public Map<String, Object> insertCalEvent(Map<String, Object> pCalEventRow) {
		Map<String, Object> insCalRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertCalEvent", pCalEventRow);
		return insCalRow;
	}

	public int updateCalEvent(Map<String, Object> pCalEventRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateCalEvent", pCalEventRow);
		return updCnt;
	}

	public int deleteCalEvent(String pUserId, String pIljeongSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("USER_ID", pUserId);
		paramMap.put("ILJEONG_SEQ", pIljeongSeq);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteCalEvent", paramMap);
		return delCnt;
	}
	
}