package kr.co.codefarm.svcm.systemmanager.corp.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author		: wjjoo
 * @Date		: 2024. 4. 1.
 * @Description	: 기업, 기관 관리
 */
@Service
public class CorpService {
	
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.corp";
	
	public List<Map<String, Object>> getCorpList(Map<String, Object> paramMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getCorpList", paramMap);
	}
	
	public List<Map<String, Object>> getAuthList(Map<String, Object> paramMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAuthList", paramMap);
	}

	public int insertAuth(Map<String, Object> paramMap) {
		return commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAuth", paramMap);
	}

	public int deleteAuth(Map<String, Object> paramMap) {
		return commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteAuth", paramMap);
	}
	
	public List<Map<String, Object>> getCorpAuthList(Map<String, Object> paramMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getCorpAuthList", paramMap);
	}

	public int updateCorpUserAuth(Map<String, Object> paramMap) {
		if(!paramMap.get("SYSTEM_ID").equals(paramMap.get("USER_ID")) && commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAuthCheck", paramMap).size() > 0) {
			List<Map<String, Object>> userAuthList = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getCorpUserAuthList", paramMap);
			for(Map<String, Object> item : userAuthList) {
				System.out.println(item);
				if(item.get("ROLE_CD").equals("U0002") || item.get("ROLE_CD").equals("U0003")) {
					return commonDao.update(DEFAULT_MAPPER_PATH + ".updateCorpUserAuth", paramMap);
				}
			}
		}
		return 0;
	}
}
