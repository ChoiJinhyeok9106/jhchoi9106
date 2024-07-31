/**
 * 
 */
package kr.co.codefarm.svcm.amanager.common.service;

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
public class CommonsearchService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.common";


	
	public List<Map<String, Object>> getDeptList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getDeptList", pMap);
	}
	
	public List<Map<String, Object>> getAsstNoList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAsstNoList", pMap);
	}
	
	public List<Map<String, Object>> getUsrList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUsrList", pMap);
	}
	
	public List<Map<String, Object>> getDeptTreeList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getDeptTreeList", pMap);
	}
	
}
