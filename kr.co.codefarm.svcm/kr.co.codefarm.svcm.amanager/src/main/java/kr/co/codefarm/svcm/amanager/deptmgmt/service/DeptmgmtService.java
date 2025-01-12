/**
 * 
 */
package kr.co.codefarm.svcm.amanager.deptmgmt.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import kr.co.codefarm.svcm.commons.service.file.FileService;
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
public class DeptmgmtService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.deptmgmt";
	

	public List<Map<String, Object>> getDeptMgmtList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getDeptMgmtList", pMap);
	}
	
	public int insertDept(Map<String, Object> pDeptMgmtRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertDept", pDeptMgmtRow);
		return insCnt;
	}
	
	public int updateDept(Map<String, Object> pDeptMgmtRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateDept", pDeptMgmtRow);
		return updCnt;
	}
	
	public List<Map<String, Object>> checkDeptMgmt(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".checkDeptMgmt", pMap);
	}
	
	public int deptUseNot(String pDeptCd) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("deptCd", pDeptCd);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deptUseNot", paramMap);
		return delCnt;
	}

}
