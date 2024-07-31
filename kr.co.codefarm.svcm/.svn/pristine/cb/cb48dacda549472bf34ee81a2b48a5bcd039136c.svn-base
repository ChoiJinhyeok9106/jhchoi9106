/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.vacation.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author hrchoi
 *
 */
@Service
public class VacationService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.vacation";


	public List<Map<String, Object>> getVacationList(Map<String, Object> paramMap) {
	    List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getVacationList", paramMap);
	    return list;
    }
	public List<Map<String, Object>> getExistsYear(Map<String, Object> paramMap) {
	    List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getExistsYear", paramMap);
	    return list;
    }

	public int putVacationList(Map<String, Object> pRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".putVacationList", pRow);
		return updCnt;
	}


	public int updateUsedVacation(Map<String, Object> pRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUsedVacation", pRow);
		return updCnt;
	}

	public int insertVacacion(Map<String, Object> pRow) {
		int updCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertVacacion", pRow);
		return updCnt;
	}

}
