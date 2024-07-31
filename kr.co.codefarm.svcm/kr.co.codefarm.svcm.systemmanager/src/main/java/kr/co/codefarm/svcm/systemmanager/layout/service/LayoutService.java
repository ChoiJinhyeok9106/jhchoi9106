/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.layout.service;

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
public class LayoutService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.layout";
	
	//Layout List
	public List<Map<String, Object>> getLayoutList(String pUserId, String pDefaultAuthCd, Map<String, Object> paramMap) {
		paramMap.put("USER_ID", pUserId);
		paramMap.put("DEFAULT_AUTH_CD", pDefaultAuthCd);
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectLayoutList", paramMap);
		return list;
	}

	public Map<String, Object>  insertLayout(Map<String, Object> pLayoutRow) {
		Map<String, Object> insLayoutRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertLayout", pLayoutRow);
		return insLayoutRow;
	}
	
	public int updateLayout(Map<String, Object> pLayoutRow) {
		int updCnt = 0;
		
		if(pLayoutRow.containsKey("JSON_DATA_LIST")){
			List<Map<String, Object>> layoutList = (List<Map<String, Object>>) pLayoutRow.get("JSON_DATA_LIST");
			for(int i=0 ; i < layoutList.size() ; i++) {
				layoutList.get(i).put("SYSTEM_ID", String.valueOf(pLayoutRow.get("SYSTEM_ID")));
				layoutList.get(i).put("SYSTEM_IP", String.valueOf(pLayoutRow.get("SYSTEM_IP")));
				layoutList.get(i).put("SYSTEM_PGM", String.valueOf(pLayoutRow.get("SYSTEM_PGM")));
			}
			updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateLayout", layoutList);
		}else {
			updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateLayout", pLayoutRow);
		}
		
		return updCnt;
	}
	
	public int deleteLayout(String pUserId, String pUserPageSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", pUserId);
		paramMap.put("USER_PAGE_SEQ", pUserPageSeq);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteLayout", paramMap);
		return delCnt;
	}
	
	//Layout List
	public List<Map<String, Object>> getLayoutContentList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectLayoutContentList", paramMap);
		return list;
	}

	public int insertLayoutContent(String pUserId, Map<String, Object> pLayoutContentRow) {
		int insertCnt = 0;
		
		if(pLayoutContentRow.containsKey("JSON_DATA_LIST")){
			List<Map<String, Object>> layoutContentList = (List<Map<String, Object>>) pLayoutContentRow.get("JSON_DATA_LIST");
			for(int i=0 ; i < layoutContentList.size() ; i++) {
				if(i == 0) {
					String pUserPageSeq = String.valueOf(layoutContentList.get(i).get("USER_PAGE_SEQ"));
					String userId = String.valueOf(layoutContentList.get(i).get("USER_ID"));
					
					deleteLayoutContent(userId, pUserPageSeq, null);
				}

				layoutContentList.get(i).put("SYSTEM_ID", String.valueOf(pLayoutContentRow.get("SYSTEM_ID")));
				layoutContentList.get(i).put("SYSTEM_IP", String.valueOf(pLayoutContentRow.get("SYSTEM_IP")));
				layoutContentList.get(i).put("SYSTEM_PGM", String.valueOf(pLayoutContentRow.get("SYSTEM_PGM")));
				layoutContentList.get(i).put("ARRAY_SEQ", i);
			}
			
			insertCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertLayoutContent", layoutContentList);
		}else {
			String pUserPageSeq = String.valueOf(pLayoutContentRow.get("USER_PAGE_SEQ"));
			String userId = String.valueOf(pLayoutContentRow.get("USER_ID"));
			deleteLayoutContent(userId, pUserPageSeq, null);
			insertCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertLayoutContent", pLayoutContentRow);
		}
		
		return insertCnt;
	}
	
	public int updateLayoutContent(Map<String, Object> pLayoutContentRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateLayoutContent", pLayoutContentRow);
		return updCnt;
	}
	
	public int deleteLayoutContent(String pUserId, String pUserPageSeq, String pLayoutContentSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("USER_ID", pUserId);
		paramMap.put("USER_PAGE_SEQ", pUserPageSeq);
		paramMap.put("LAYOUT_CONTENT_SEQ", pLayoutContentSeq);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteLayoutContent", paramMap);
		return delCnt;
	}
	public List<Map<String, Object>> getPopupCount(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getPopupCount", paramMap);
		return list;
	}
}
