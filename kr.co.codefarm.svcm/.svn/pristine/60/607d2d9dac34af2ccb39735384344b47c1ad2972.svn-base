package kr.co.codefarm.svcm.systemmanager.service.service;

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
public class ServiceService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.service";
	
	public List<Map<String, Object>> getServiceListForSystem(String pServiceCd, String pServiceNm, String pCommcodeUseYn) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("SERVICE_CD", pServiceCd);
		paramMap.put("SERVICE_NM", pServiceNm);
		paramMap.put("COMMCODE_USE_YN", pCommcodeUseYn);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getServiceListForSystem", paramMap);
		return list;
	}
	
	public List<Map<String, Object>> getCommCodeUseList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getCommCodeUseList", paramMap);
		return list;
	}

	public List<Map<String, Object>> getServiceList(String pSearchType, String pSearchArgv, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getServiceList", paramMap);
		return list;
	}
	
	public Object getService(String pServiceCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("serviceCd", pServiceCd);
		Object service = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getService", paramMap);
		return service;
	}
	
	public int deleteService(String pServiceCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("serviceCd", pServiceCd);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteService", paramMap);
		return delCnt;
	}
	
	public int updateService(Map<String, Object> pServiceRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateService", pServiceRow);
		return updCnt;
	}
	
	public int insertService(Map<String, Object> pServiceRow) {
		Map<String, Object> paramMap = new HashMap<>();
		
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertService", pServiceRow);
		return insCnt;
	}
}
