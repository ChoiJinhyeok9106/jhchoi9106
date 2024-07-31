/**
 * 
 */
package kr.co.codefarm.svcm.amanager.electag.service;

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
public class ElectagService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.electag";


	// 시스템 관리 > 로그 관리 > 로그 목록 조회
	public List<Map<String, Object>> getElecTagReqStatusList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getElecTagReqStatusList", pMap);
	}
	
	public List<Map<String, Object>> getElecTagIssueStatusList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getElecTagIssueStatusList", pMap);
	}
	
	public List<Map<String, Object>> getElecTagIssueStatusDetlQry(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getElecTagIssueStatusDetlQry", pMap);
	}
	
	public int updateTaPrtIssu(Map<String, Object> dd) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateTaPrtIssu", dd);
		return updCnt;
	}
}
