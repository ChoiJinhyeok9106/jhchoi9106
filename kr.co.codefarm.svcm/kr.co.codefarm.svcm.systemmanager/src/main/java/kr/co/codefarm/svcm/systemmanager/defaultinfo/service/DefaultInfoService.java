/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.defaultinfo.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import kr.co.codefarm.svcm.commons.service.file.FileService;
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
public class DefaultInfoService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.defaultinfo";
	

	public List<Map<String, Object>> getDefaultInfoList(String pSearchType, String pSearchArgv, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getDefaultinfoList", paramMap);
		return list;
	}
	
	public Object getRepresentative(String pSiteSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("SITE_SEQ", pSiteSeq);
		Object representative = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getRepresentative", paramMap);
		return representative;
//		return DefaultInfoMap.getMap();
	}
	
	public int insertDefaultInfo(Map<String, Object> pDefaultInfoRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertDefaultInfo", pDefaultInfoRow);
		return insCnt;
	}
	
	public int updateDefaultInfo(Map<String, Object> pDefaultInfoRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateDefaultInfo", pDefaultInfoRow);
		return updCnt;
	}
	
	public int insertHistory(Map<String, Object> paramMap) {
		int insCnt = 0;
		Map<String, Object> ret = (Map<String, Object>) commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getLastHistory", paramMap);
		try {
			if(!ret.get("TITLE").equals(paramMap.get("TITLE")) || !ret.get("CONTENTS").equals(paramMap.get("CONTENTS")))
				insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertHistory", paramMap);
		}catch(NullPointerException e) {
			e.printStackTrace();
			insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertHistory", paramMap);
		}
		
		return insCnt;
	}
	
	public int deleteDefaultInfo(String pSiteSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("siteSeq", pSiteSeq);
		
		List<Map<String, Object>> representList = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getRepresentList", paramMap);
		
		for(int i = 0 ; i < representList.size(); i++) {
			Map<String, Object> representMap = representList.get(i);
			String tempSiteSeq = representMap.get("SITE_SEQ").toString();
			String tempDaepyojaGb = representMap.get("DAEPYOJA_GB").toString();
			String tempDaepyojaSeq = representMap.get("DAEPYOJA_SEQ").toString();
			
			fileService.deleteFile("SMN", "SMN_DAEPYOJA", tempSiteSeq+tempDaepyojaGb+tempDaepyojaSeq, "pic", "");
			fileService.deleteFile("SMN", "SMN_DAEPYOJA", tempSiteSeq+tempDaepyojaGb+tempDaepyojaSeq, "stamp", "");
		}
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteDefaultInfo", paramMap);
		
		return delCnt;
	}
}
