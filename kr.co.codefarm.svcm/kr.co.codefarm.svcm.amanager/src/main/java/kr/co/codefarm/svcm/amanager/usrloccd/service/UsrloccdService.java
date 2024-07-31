/**
 * 
 */
package kr.co.codefarm.svcm.amanager.usrloccd.service;

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
public class UsrloccdService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.usrloccd";
	
	// 사용위치관리 건물,층 리스트 조회
	public List<Map<String, Object>> getBldgList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getBldgList", pMap);
	}
	
	// 사용위치관리 위치 리스트 조회
	public List<Map<String, Object>> getLocList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getLocList", pMap);
	}
	
	// 사용위치관리 건물 입력
	public int insertBldg(Map<String, Object> pDeptMgmtRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertBldg", pDeptMgmtRow);
		return insCnt;
	}
	
	// 사용위치관리 건물 수정
	public int updateBldg(Map<String, Object> pDeptMgmtRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBldg", pDeptMgmtRow);
		return updCnt;
	}
	
	// 사용위치관리 층 입력
	public int insertStair(Map<String, Object> pDeptMgmtRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertStair", pDeptMgmtRow);
		return insCnt;
	}
	
	// 사용위치관리 층 수정
	public int updateStair(Map<String, Object> pDeptMgmtRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateStair", pDeptMgmtRow);
		return updCnt;
	}
	
	// 사용위치관리 위치 코드사용확인
	public List<Map<String, Object>> checkCodeList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".checkCodeList", pMap);
	}
		
	// 사용위치관리 위치 입력
	public int insertLoc(Map<String, Object> pDeptMgmtRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertLoc", pDeptMgmtRow);
		return insCnt;
	}
	
	// 사용위치관리 위치 수정
	public int updateLoc(Map<String, Object> pDeptMgmtRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateLoc", pDeptMgmtRow);
		return updCnt;
	}

		

}
