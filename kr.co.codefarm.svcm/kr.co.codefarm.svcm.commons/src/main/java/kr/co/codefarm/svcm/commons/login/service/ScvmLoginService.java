package kr.co.codefarm.svcm.commons.login.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScvmLoginService {
	
	@Autowired
	private ICommonMainDao commonDao;
	
	public List<Map<String, Object>> getUser(Map<String, Object> param) {
		return commonDao.selectList("kr.co.codefarm.svcm.commons.{databse.main}.login.selectUser", param);
	}

	public List<Map<String, Object>> getTokenUser(Map<String, Object> param) {
		return commonDao.selectList("kr.co.codefarm.svcm.commons.{databse.main}.login.selectTokenUser", param);
	}
	
	public List<Map<String, Object>> getRole(Map<String, Object> param) {
		return commonDao.selectList("kr.co.codefarm.svcm.commons.{databse.main}.login.selectRole", param);
	}
	
	public List<Map<String, Object>> loginDupCheck(Map<String, Object> param) {
		return commonDao.selectList("kr.co.codefarm.svcm.commons.{databse.main}.login.loginDupCheck", param);
	}
	
	public int setAccount(Map<String, Object> param) {
		return commonDao.update("kr.co.codefarm.svcm.commons.{databse.main}.login.updateAccount", param);
	}
	
	public int updateToken(Map<String, Object> param) {
		return commonDao.update("kr.co.codefarm.svcm.commons.{databse.main}.login.updateToken", param);
	}
	
	public int insertLog(Map<String, Object> param) {
		return commonDao.insert("kr.co.codefarm.svcm.systemmanager.{databse.main}.alert.insertLog", param);
	}
	
}
