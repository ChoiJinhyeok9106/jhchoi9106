/**
 * 
 */
package kr.co.codefarm.svcm.amanager.codemgmt.service;

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
public class CodemgmtService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.codemgmt";
	
	// 공통코드 리스트 조회
	public List<Map<String, Object>> getCodeMgmtList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getCodeMgmtList", pMap);
	}
	
	// 공통코드 리스트 상세 조회
	public List<Map<String, Object>> getCodeMgmtListDetail(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getCodeMgmtListDetail", pMap);
	}
	
	// 정렬순서 중복 체크
	public List<Map<String, Object>> checkOrderSeq(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".checkOrderSeq", pMap);
	}
	
	// 상세코드 중복 체크
	public List<Map<String, Object>> checkCodeList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".checkCodeList", pMap);
	}
	
	// 공통상세코드 입력
	public int insertCodeMgmtDetail(Map<String, Object> pDeptMgmtRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertCodeMgmtDetail", pDeptMgmtRow);
		return insCnt;
	}
	
	// 공통상세코드 수정
	public int updateCodeMgmtDetail(Map<String, Object> pDeptMgmtRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateCodeMgmtDetail", pDeptMgmtRow);
		return updCnt;
	}
		

}
