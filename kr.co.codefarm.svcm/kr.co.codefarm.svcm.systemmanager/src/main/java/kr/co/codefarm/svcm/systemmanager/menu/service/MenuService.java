package kr.co.codefarm.svcm.systemmanager.menu.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.menu";

	//Menu Service
	public List<Map<String, Object>> getMenuList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectMenuList", paramMap);
		return list;
	}

	//Menu Service
	public List<Map<String, Object>> getAuthMenuList(Map<String, Object> paramMap, String userDefaultAuthCd) {
		paramMap.put("DEFAULT_AUTH_CD", userDefaultAuthCd);
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".authMenuList", paramMap);
		return list;
	}
	
	public Map<String, Object>  insertMenu(Map<String, Object> pMenuRow) {
		Map<String, Object> insMenuRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertMenu", pMenuRow);
		return insMenuRow;
	}
	
	public int updateMenu(Map<String, Object> pMenuRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateMenu", pMenuRow);
		return updCnt;
	}
	
	public int deleteMenu(Map<String, Object> pMenuRow) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteMenu", pMenuRow);
		return delCnt;
	}
	
	//MenuAUth Service
	public List<Map<String, Object>> getMenuAuthList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectMenuAuthList", paramMap);
		return list;
	}
	
	public Map<String, Object>  insertMenuAuth(Map<String, Object> pMenuRow) {
		Map<String, Object> insMenuRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertMenuAuth", pMenuRow);
		return insMenuRow;
	}
	
	public int updateMenuAuth(Map<String, Object> pMenuRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateMenuAuth", pMenuRow);
		return updCnt;
	}
	
	public int deleteMenuAuth(Map<String, Object> pMenuRow) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteMenuAuth", pMenuRow);
		return delCnt;
	}
	
	//SubPgm Service
	public List<Map<String, Object>> getSubPgmList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".selectSubPgmList", paramMap);
		return list;
	}
	
	public Map<String, Object>  insertSubPgm(Map<String, Object> pMenuRow) {
		Map<String, Object> insMenuRow = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertSubPgm", pMenuRow);
		return insMenuRow;
	}
	
	public int updateSubPgm(Map<String, Object> pMenuRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateSubPgm", pMenuRow);
		return updCnt;
	}
	
	public int deleteSubPgm(Map<String, Object> pMenuRow) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteSubPgm", pMenuRow);
		return delCnt;
	}
	
	public List<Map<String, Object>> getAppMenuManualList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAppMenuManualList", paramMap);
		return list;
	}
	
	public int insertAppMenuManual(Map<String, Object> pAppRow) {
		int saveCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAppMenuManual", pAppRow);
		return saveCnt;
	}
	
	public int updateAppMenuManual(Map<String, Object> pAppRow) {
		int saveCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAppMenuManual", pAppRow);
		return saveCnt;
	}
	
	public int deleteAppMenuManual(String pAppId, String pMenuCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("appId", pAppId);
		paramMap.put("menuCd", pMenuCd);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteAppMenuManual", paramMap);
		return delCnt;
	}

	public int insertMenuAuthBatch(Map<String, Object> paramMap) {
		int saveCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertMenuAuthBatch", paramMap);
		return saveCnt;
	}

	public int deleteMenuAuthBatch(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteMenuAuthBatch", paramMap);
		return delCnt;
	}
}
