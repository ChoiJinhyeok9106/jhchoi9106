/**
 * 
 */
package kr.co.codefarm.svcm.amanager.gdscdmgmt.service;

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
public class GdscdmgmtService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.gdscdmgmt";
	
	// 물품분류 조회
	public List<Map<String, Object>> getAssetClList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAssetClList", pMap);
	}
	
	// 물품규격 조회
	public List<Map<String, Object>> getAssetStndList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getAssetStndList", pMap);
	}
	
	// 물품분류 입력
	public int insertAssetClCd(Map<String, Object> pGdscdmgmtRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAssetClCd", pGdscdmgmtRow);
		return insCnt;
	}
	
	// 물품분류 수정
	public int updateAssetClCd(Map<String, Object> pGdscdmgmtRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAssetClCd", pGdscdmgmtRow);
		return updCnt;
	}
	
	// 물품분류 삭제시 사용여부체크
	public List<Map<String, Object>> checkUseAssetClCd(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".checkUseAssetClCd", pMap);
	}
	
	// 물품분류 삭제
	public int deleteAssetClCd(String pAssetClNo) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("assetClNo", pAssetClNo);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteAssetClCd", paramMap);
		return delCnt;
	}
	
	// 물품규격번호 중복체크
	public List<Map<String, Object>> checkAssetStndList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".checkAssetStndList", pMap);
	}
	
	
	// 물품규격 입력
	public int insertAssetStndCd(Map<String, Object> pGdscdmgmtRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAssetStndCd", pGdscdmgmtRow);
		return insCnt;
	}
	
	// 물품규격 수정
	public int updateAssetStndCd(Map<String, Object> pGdscdmgmtRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAssetStndCd", pGdscdmgmtRow);
		return updCnt;
	}
	
	// 물품규격 삭제시 사용여부체크
	public List<Map<String, Object>> checkUseAssetStndCd(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".checkUseAssetStndCd", pMap);
	}
	
	// 물품규격 삭제
	public int deleteAssetStndCd(Map<String, Object> pGdscdmgmtRow) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteAssetStndCd", pGdscdmgmtRow);
		return delCnt;
	}	
}
