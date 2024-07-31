/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.common.service;

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
public class CommonService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.common";

	public List<Map<String, Object>> getCommonList(String pServiceType, String pSearchType, String pSearchArgv, String pComCodeUpCd, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pServiceType", pServiceType);
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pComCodeUpCd", pComCodeUpCd);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectList", paramMap);
		return list;
	}
	
	public Object getCommon(String pUpCd, String pCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("UP_CD", pUpCd);
		paramMap.put("CD", pCd);
		
		Object common = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".select", paramMap);
		return common;
	}

	
	public int updateCommon(Map<String, Object> pCommonRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateCommon", pCommonRow);
		return updCnt;
	}
	
	public int insertCommon(Map<String, Object> pCommonRow) {
		Map<String, Object> paramMap = new HashMap<>();
		
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertCommon", pCommonRow);
		return insCnt;
	}
	
	public int deleteCommon(String pUpCd, String pCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("UP_CD", pUpCd);
		paramMap.put("CD", pCd);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteCommon", paramMap);
		return delCnt;
	}

	public List<Map<String, Object>> getServiceList() {
		Map<String, Object> paramMap = new HashMap<>();
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectServiceList", paramMap);
		return list;
	}

	public Object getCustomCd(String cd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("CD", cd);
		Object service = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".selectCustomCdList", paramMap);
		return service;
	}

	public int updateCustomCd(Map<String, Object> pCustomBtn) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateCustomBtn", pCustomBtn);
		return updCnt;
	}
}
