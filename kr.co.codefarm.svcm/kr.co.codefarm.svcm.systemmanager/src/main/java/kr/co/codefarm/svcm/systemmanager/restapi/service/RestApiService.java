/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.restapi.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YJAHN
 *
 */
@Service
public class RestApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.restapi";

	public List<Map<String, Object>> getRestApiList(String pSearchArgv, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		//pPageCount = "5";
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getRestApiList", paramMap);
		return list;
	}
	
	public int insertRestApi(Map<String, Object> pRestApiRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertRestApi", pRestApiRow);
		return insCnt;
	}
	
	public int updateRestApi(Map<String, Object> pRestApiRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateRestApi", pRestApiRow);
		return updCnt;
	}
	
	public int deleteRestApi(String pApiSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("apiSeq", pApiSeq);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteRestApi", paramMap);
		return delCnt;
	}
}
