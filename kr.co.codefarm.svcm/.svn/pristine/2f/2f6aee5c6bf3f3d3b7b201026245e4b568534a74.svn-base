/**
 * 
 */
package kr.co.codefarm.svcm.amanager.useratymgmt.service;

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
public class UseratymgmtService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.useratymgmt";
	
	
	// 사용자관리 리스트 조회
	public List<Map<String, Object>> getUsrAtyMgmtList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUsrAtyMgmtList", pMap);
	}
	
	// 권한 리스트 조회(select)
	public List<Map<String, Object>> getAuthorCodeList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAuthorCodeList", pMap);
	}
	
	// 사용자관리 입력
	public int insertUsrMgmt(Map<String, Object> pUsrMgmtRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertUsrMgmt", pUsrMgmtRow);
		return insCnt;
	}
	
	// 사용자관리 수정
	public int updateUsrMgmt(Map<String, Object> pUsrMgmtRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateUsrMgmt", pUsrMgmtRow);
		return updCnt;
	}	
	
}
