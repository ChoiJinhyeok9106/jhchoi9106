/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.widget.service;

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
public class WidgetService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.widget";

	public List<Map<String, Object>> getWidgetList(String pSearchAppId, String pSearchType, String pSearchArgv, String pPageNo, String pRowCount, String pUserId, String pAuthCd) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("pSearchAppId", pSearchAppId);
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		paramMap.put("pUserId", pUserId);
		paramMap.put("pAuthCd", pAuthCd);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getWidgetList", paramMap);
		return list;
	}
	
	public Object getWidget(String pAppId, String pWidgetSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("APP_ID", pAppId);
		paramMap.put("WIDGET_SEQ", pWidgetSeq);
		Object widget = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getWidget", paramMap);
		return widget;
	}
	
	public int deleteWidget(String pAppId, String pWidgetSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("appId", pAppId);
		paramMap.put("widgetSeq", pWidgetSeq);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteWidget", paramMap);
		return delCnt;
	}
	
	public int updateWidget(Map<String, Object> pWidgetRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateWidget", pWidgetRow);
		return updCnt;
	}
	
	public Map<String, Object> insertWidget(Map<String, Object> pWidgetRow) {
		Map<String, Object> insWidgetRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertWidget", pWidgetRow);
		return insWidgetRow;
	}
	
	//위젯 권한 리스트(작성자 : 최대건(2019/06/10))
	public List<Map<String, Object>> getAuthWidget(String pAppUseYn,String pSearchType, String pSearchArgv, String pAuthCd,String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("pAppUseYn", pAppUseYn);	
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pAuthCd", pAuthCd);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getWidgetAuthList", paramMap);
		return list;
	}
}
