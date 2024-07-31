/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.log.service;

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
public class LogService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.log";

	// 시스템 관리 > 로그 관리 > 로그 목록 조회
	public List<Map<String, Object>> getLogList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getLogList", pMap);
	}

	// 시스템 관리 > 로그 관리 > 로그 통계 조회
	public List<Map<String, Object>> getLogTonggye(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getLogTonggye", pMap);
	}

	// 시스템 관리 > 로그 관리 > 접속자 수
	public List<Map<String, Object>> getUsersLogList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUsersLogList", pMap);
	}

	// 시스템 관리 > 로그 관리 > 게시판 이용자
	public List<Map<String, Object>> getBoardsLogList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getBoardsLogList", pMap);
	}

	
}
