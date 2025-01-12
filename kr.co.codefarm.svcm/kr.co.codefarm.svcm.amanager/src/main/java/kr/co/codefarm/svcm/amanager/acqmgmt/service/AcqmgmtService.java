/**
 * 
 */
package kr.co.codefarm.svcm.amanager.acqmgmt.service;

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
public class AcqmgmtService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.acqmgmt";


	// 시스템 관리 > 로그 관리 > 로그 목록 조회
	public List<Map<String, Object>> getAsstAcqMgmtList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAsstAcqMgmtList", pMap);
	}
	
	public int insertAsstAcq(Map<String, Object> pAcqMgmtRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAsstAcq", pAcqMgmtRow);
		return insCnt;
	}
	
	public int updateAsstAcq(Map<String, Object> pAcqMgmtRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAsstAcq", pAcqMgmtRow);
		return updCnt;
	}

	public int deleteAsstAcq(String pAssetAcqNo) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("assetAcqNo", pAssetAcqNo);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteAsstAcq", paramMap);
		return delCnt;
	}
	
	public int assetDtlInsert(Map<String, Object> pAcqMgmtRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".assetDtlInsert", pAcqMgmtRow);
		return insCnt;
	}
	

	public int assetAcqRflcAtUpdate(String pAssetAcqNo) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("assetAcqNo", pAssetAcqNo);
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".assetAcqRflcAtUpdate", paramMap);
		return updCnt;
	}
}
