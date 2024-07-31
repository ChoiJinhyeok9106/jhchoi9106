/**
 * 
 */
package kr.co.codefarm.svcm.amanager.haveexcel.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import kr.co.codefarm.svcm.commons.service.file.FileService;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
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
public class HaveexcelService {
	@Autowired
	private ICommonMainDao commonDao;
	
	@Autowired
	private FileService fileService;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.amanager.{databse.main}.haveexcel";

	/* 조회 */
	public List<Map<String, Object>> getHaveExcelList(Map<String, Object> pMap) {
		return commonDao.selectList(DEFAULT_MAPPER_PATH + ".getHaveExcelList", pMap);
	}

	/* 엑셀업로드 삭제 */
	public int deleteHaveExcel(String pAgencyId) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("agencyId", pAgencyId);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteHaveExcel", paramMap);
		return delCnt;
	}

	/* 엑셀 파일 데이터 입력 */
	public int insertHaveExcel(Map<String, Object> pHaveExcelRow) {
		System.out.println("insertHaveExcel pHaveExcelRow: " + pHaveExcelRow);
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertHaveExcel", pHaveExcelRow);
		return insCnt;
	}
	
	/* 필수 항목 검증 */
	public int updateMdtr(Map<String, Object> pHaveExcelRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateMdtr", pHaveExcelRow);
		return updCnt;
	}
	
	/* 자산관리번호 update */
	public int updateAssetMgmtNo(Map<String, Object> pHaveExcelRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAssetMgmtNo", pHaveExcelRow);
		return updCnt;
	}
	/* 물품분류번호 생성 */
	public int insertAsstClCd(Map<String, Object> pHaveExcelRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAsstClCd", pHaveExcelRow);
		return insCnt;
	}
	
	/* 물품규격번호 생성 */
	public int insertAsstStndCd(Map<String, Object> pHaveExcelRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAsstStndCd", pHaveExcelRow);
		return insCnt;
	}

	/* 자산 반영 이력현황 삭제 */
	public int deleteAcqDtlHist(String pAgencyId) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("agencyId", pAgencyId);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteAcqDtlHist", paramMap);
		return delCnt;
	}

	/* 자산 반영 발급현황 삭제 */
	public int deletePrtIssu(String pAgencyId) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("agencyId", pAgencyId);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deletePrtIssu", paramMap);
		return delCnt;
	}

	/* 자산 반영 발급현황 삭제 */
	public int deleteAcqDtl(String pAgencyId) {
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("agencyId", pAgencyId);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteAcqDtl", paramMap);
		return delCnt;
	}

	/* 자산 반영 보유현황 입력 */
	public int insertAcqDtl(Map<String, Object> pHaveExcelRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAcqDtl", pHaveExcelRow);
		return insCnt;
	}
	
	/* 자산 반영 취득관리 입력 */
	public int insertAcq(Map<String, Object> pHaveExcelRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertAcq", pHaveExcelRow);
		return insCnt;
	}
	
	/* 자산 반영 보유현황 수정 */
	public int updateAcqDtl(Map<String, Object> pHaveExcelRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAcqDtl", pHaveExcelRow);
		return updCnt;
	}
	
	/* 자산 반영 반영여부 수정 */
	public int updateRflcYn(Map<String, Object> pHaveExcelRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateRflcYn", pHaveExcelRow);
		return updCnt;
	}
	

}
