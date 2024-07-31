package kr.co.codefarm.svcm.systemmanager.alert.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlertService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.alert";

	//Alert Service
	public List<Map<String, Object>> getAlertList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectAlertList", paramMap);
		
		return list;
	}
	
	public Object getAlert(String pAlertSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("ALERT_SEQ", pAlertSeq);
		
		Object alert = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectAlert", paramMap);
		return alert;
	}

	public Map<String, Object>  insertAlert(Map<String, Object> pAlertRow) {
		Map<String, Object> insAlertRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertAlert", pAlertRow);
		return insAlertRow;
	}
	
	
	public int updateAlert(Map<String, Object> pAlertRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAlert", pAlertRow);
		return updCnt;
	}
	
	
	public int deleteAlert(String pAlarmSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("ALARM_SEQ", pAlarmSeq);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteAlert", paramMap);
		return delCnt;
	}
	
	
	
	
	//App Auth Service
	public List<Map<String, Object>> getDaesangList(String pAlarmSeq, String pAlarmDaesangGb) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("ALARM_SEQ", pAlarmSeq);
		paramMap.put("ALARM_DAESANG_GB", pAlarmDaesangGb);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectDaesangList", paramMap);
		return list;
	}
	
	//App Auth Service
	public List<Map<String, Object>> getDaesangUserList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectDaesangUserList", paramMap);
		return list;
	}
	
	public Object getDaesang(String pAlarmSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("ALARM_SEQ", pAlarmSeq);
		
		Object daesang = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectDaesang", paramMap);
		return daesang;
	}

	public int insertDaesang(Map<String, Object> pDaesangRow) {
		Map<String, Object> paramMap = new HashMap<>();
		
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertDaesang", pDaesangRow);
		return insCnt;
	}
	
	public int updateDaesang(Map<String, Object> pDaesangRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateDaesang", pDaesangRow);
		return updCnt;
	}
	
	
	public int deleteDaesang(String pAlarmSeq, String pAlarmDaesangGb, String pDaesangjaId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("ALARM_SEQ", pAlarmSeq);
		paramMap.put("ALARM_DAESANG_GB", pAlarmDaesangGb);
		paramMap.put("DAESANGJA_ID", pDaesangjaId);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteDaesang", paramMap);
		return delCnt;
	}
	
	//Alert Service
	public List<Map<String, Object>> getUserAlarmList(String pAlarmGb, String pUserId, String pDefaultAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("pAlarmGb", pAlarmGb);
		paramMap.put("USER_ID", pUserId);
		paramMap.put("AUTH_CD", pDefaultAuthCd);
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectUserAlarmList", paramMap);
		return list;
	}
	
	public Object getUserAlarm(String pAlarmSeq, String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("ALARM_SEQ", pAlarmSeq);
		paramMap.put("USER_ID", pUserId);
		
		Object alert = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectUserAlarm", paramMap);
		return alert;
	}
	
	public int insertUserAlarm(Map<String, Object> pAlertRow, String pUserId) {
		int insertCnt = 0;
		
		if(pAlertRow.containsKey("data")){
			List<Map<String, Object>> alertList = (List<Map<String, Object>>) pAlertRow.get("data");
			for(int i=0 ; i < alertList.size() ; i++) {
				alertList.get(i).put("USER_ID", pUserId);
				alertList.get(i).put("SYSTEM_ID", String.valueOf(pAlertRow.get("SYSTEM_ID")));
				alertList.get(i).put("SYSTEM_IP", String.valueOf(pAlertRow.get("SYSTEM_IP")));
				alertList.get(i).put("SYSTEM_PGM", String.valueOf(pAlertRow.get("SYSTEM_PGM")));
			}
			
			insertCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUserAlarm", alertList);
		}else {
			pAlertRow.put("USER_ID", pUserId);
			insertCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUserAlarm", pAlertRow);
		}
		
		return insertCnt;
	}
	
	
	public int updateUserAlarm(Map<String, Object> pAlertRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUserAlarm", pAlertRow);
		return updCnt;
	}
	
	public int deleteUserAlarm(String pAlarmSeq, String pUserId) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("ALARM_SEQ", pAlarmSeq);
		paramMap.put("USER_ID", pUserId);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteUserAlarm", paramMap);
		return delCnt;
	}
	
	
	//Alert Service
	public List<Map<String, Object>> getUserAlarmDataList(String pUserId, String pAlarmMinTime) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("USER_ID", pUserId);
		paramMap.put("ALARM_DTTM", pAlarmMinTime);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectUserAlarmDataList", paramMap);
		commonDao.update(DEFAULT_MAPPER_PATH + ".updateUserAlarmHwakinData", list);
		return list;
	}
	
	public String insertUserAlarmData(List<Map<String, Object>> alarmDataList, String pAlarmSeq) {
		List<Map<String, Object>> alarmBlockList = new ArrayList<Map<String, Object>>();
		Map<String, Object> paramMap = new HashMap<>();
		String curDate = "";
		int insCnt = 0;
		 
		paramMap.put("ALARM_SEQ", pAlarmSeq);
		
		curDate = (String) commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getDate");
		alarmBlockList = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectAlarmBlockList", paramMap);
		insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUserAlarmData", alarmDataList);

		return curDate;
	}
	
	public int updateUserAlarmData(Map<String, Object> pAlertRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUserAlarmData", pAlertRow);
		return updCnt;
	}
	
	public List<Map<String, Object>> getAllAlarmList() {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectAllAlarmList");
	}
	
	public List<Map<String, Object>> getAuthUserList(String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("AUTH_CD", pAuthCd);
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectAuthUserList", paramMap);
		return list;
	}
	
	public int insertLog(Map<String, Object> pProgramRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertLog", pProgramRow);
		return insCnt;
	}

	public int marketAlarmUpdate(Map<String, Object> pAlertRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".marketAlarmUpdate", pAlertRow);
		return updCnt;
	}

	public List<Map<String, Object>> marketAlarm(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".marketAlarm", paramMap);

		return list;
	}
}
