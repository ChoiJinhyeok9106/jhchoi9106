/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.popupmanager.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author syyoon
 *
 */
@Service
public class PopupmanagerService {
	@Autowired
	private ICommonMainDao commonDao;

	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.popupmanager";

	public List<Map<String, Object>> searchPopup(Map<String, Object> map) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".searchPopup",map);
		return list;
	}

	public int popupInsert(Map<String, Object> map) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".popupInsert", map);
		return insCnt;
	}

	public int popupUpdate(Map<String, Object> map) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".popupUpdate", map);
		return updCnt;
	}

	public int useYnToggle(Map<String, Object> map) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".useYnToggle", map);
		return updCnt;
	}

	public int popupDelete(Map<String, Object> map) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".popupDelete", map);
		return delCnt;
	}

}
