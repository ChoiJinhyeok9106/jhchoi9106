/**
 * 
 */
package kr.co.codefarm.svcm.amanager.havestatus.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import kr.co.codefarm.svcm.commons.service.file.FileService;

/**
 * @author YJAHN
 *
 */
@Service
public class HaveStatusService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.havestatus";


	// 시스템 관리 > 로그 관리 > 로그 목록 조회
	public List<Map<String, Object>> getHaveStatusList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getHaveStatusList", pMap);
	}
	
	//수정
	public int updateHaveStatus(Map<String, Object> pHaveStatusRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateHaveStatus", pHaveStatusRow);
		return updCnt;
	}
	
	// 태그 발급 요청
	public int insertTaPrtIssuReq(Map<String, Object> pHaveStatusRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertTaPrtIssuReq", pHaveStatusRow);
		return insCnt;
	}
	
	// 태그 발급 취소
	public int updateTaPrtIsuuReqcncl(Map<String, Object> pHaveStatusRow) {
		int insCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateTaPrtIsuuReqcncl", pHaveStatusRow);
		return insCnt;
	}
	
	// 사용 위치 조회
	public List<Map<String, Object>> getUseLoc(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getUseLoc", pMap);
	}
	
	
}
