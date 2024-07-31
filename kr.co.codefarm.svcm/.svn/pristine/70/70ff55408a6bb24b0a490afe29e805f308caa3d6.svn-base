/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.representative.service;

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
public class RepresentAtiveService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.representative";

	public List<Map<String, Object>> getDefaultInfoList(String pSearchType, String pSearchArgv) {
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getDefaultinfoList", paramMap);
		return list;
	}
	
	public List<Map<String, Object>> getRepresentAtivList(String pSiteSeq) {
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pSiteSeq", pSiteSeq);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getRepresentAtivList", paramMap);
		return list;
	}
	
	/*대표자 중복 체크*/
	public List<Map<String, Object>> getRepresentAtivCheck(String pSiteSeq, String pDaepyojaGb, String pFrdt, String pTodt) {
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pSiteSeq", pSiteSeq);
		paramMap.put("pDaepyojaGb", pDaepyojaGb);
		paramMap.put("pFrdt", pFrdt);
		paramMap.put("pTodt", pTodt);
		
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getCount", paramMap);
		
		return list;
	}
	public int insertRepresentAtiv(Map<String, Object> pRepresentAtivRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertRepresentAtiv", pRepresentAtivRow);
		return insCnt;
	}
	
	
	  
	public int updateRepresentAtiv(Map<String, Object> pRepresentAtivRow) {
		
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateRepresentAtiv", pRepresentAtivRow);
		
		return updCnt;
	}
	  
	
	public int deleteRepresentAtiv(String pSiteSeq, String pDaepyojaGb, String pDaepyojaSeq) { 
  		Map<String, Object> paramMap = new HashMap<>();
  		
  		paramMap.put("SITE_SEQ", pSiteSeq); 
  		paramMap.put("DAEPYOJA_GB", pDaepyojaGb); 
  		paramMap.put("DAEPYOJA_SEQ", pDaepyojaSeq); 
  
  		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteRepresentAtiv", paramMap);
  
  		return delCnt; 
	 }
}
