/**
 * 
 */
package kr.co.codefarm.svcm.amanager.havestatushist.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import kr.co.codefarm.svcm.commons.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Map;

/**
 * @author YJAHN
 *
 */
@Service
public class HavestatushistService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.havestatushist";


	// 
	public List<Map<String, Object>> getHaveStatusHistList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getHaveStatusHistList", pMap);
	}
	
}
