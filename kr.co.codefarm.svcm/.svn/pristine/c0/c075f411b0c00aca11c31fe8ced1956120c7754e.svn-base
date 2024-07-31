package kr.co.codefarm.svcm.commons.service.comm;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScvmService {

	@Autowired
	private ICommonMainDao commonDao;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.commons.{databse.main}.login";
    
	
	/**
	 * 약관 동의
	 */
	public int agree(Map<String, Object> paramMap) {
		return commonDao.update(DEFAULT_MAPPER_PATH + ".updateAgree", paramMap);
	}
	
}
