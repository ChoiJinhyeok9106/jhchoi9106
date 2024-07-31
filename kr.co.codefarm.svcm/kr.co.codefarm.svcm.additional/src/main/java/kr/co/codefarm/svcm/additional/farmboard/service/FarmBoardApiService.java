/**
 * 
 */
package kr.co.codefarm.svcm.additional.farmboard.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@Service
public class FarmBoardApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.additional.{databse.main}.farmboardApi";
	
	//팜보드 메인
	public List<Map<String, Object>> getFarmBoardMainList(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardMainList", paramMap);
		return list;
	}
	
	//팜보드 리스트
	public List<Map<String, Object>> getFarmBoardListList(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardListList", paramMap);
		return list;
	}
		
	//팜보드 카드리스트
	public List<Map<String, Object>> getFarmBoardCardList(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardCardList", paramMap);
		return list;
	}
	
	//팜보드 카드리스트
	public List<Map<String, Object>> getFarmCardList(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmCardList", paramMap);
		return list;
	}
	
	//팜보드 카드 디테일 리스트
	public List<Map<String, Object>> getFarmBoardCardDetailList(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardCardDetailList", paramMap);
		return list;
	}

	public Map<String, Object> insertFarmBoard(Map<String, Object> pFarmboardRow) {
		Map<String, Object> insResultData = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertFarmBoard", pFarmboardRow);
		return insResultData;
	}
	
	//팜포드 참여자
	public int insertFarmBoardChamyeoja(Map<String, Object> pFarmboardRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertFarmBoardChamyeoja", pFarmboardRow);
		return insCnt;
	}
	
	public int insertFarmBoardList(Map<String, Object> pFarmboardRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertFarmBoardList", pFarmboardRow);
		return insCnt;
	}
	
	/*insertFarmBoardCard*/
	public int insertFarmBoardCard(Map<String, Object> pFarmboardRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertFarmBoardCard", pFarmboardRow);
		return insCnt;
	}
	
	/* 팜보드 리스트 참여자 */
	public int insertFarmBoardListChamyeoja(Map<String, Object> pFarmboardRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertFarmBoardListChamyeoja", pFarmboardRow);
		return insCnt;
	}
	
	
	//팜보드 참여자 조회
	public List<Map<String, Object>> getFarmboardChamyeoja(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmboardChamyeoja", paramMap);
		return list;
	}
	
	//리스트 참여자 조회
	public List<Map<String, Object>> getFarmboardListChamyeoja(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmboardListChamyeoja", paramMap);
		return list;
	}
	
	/* 비교과 수강자 팜보드 삭제 */
	public int deleteNcuFarmBoardChamyeoja(Map<String, Object> paramMap) {
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteNcuFarmBoardChamyeoja", paramMap);
		return delCnt;
	}
		
	public int deleteFarmBoard(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteFarmBoard", paramMap);
		return delCnt;
	}	
	
	/* 리스트 존재여부 */
	public List<Map<String, Object>> getFbList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFbList", paramMap);
		return list;
	}
	
	/* 카드 존재여부 */
	public List<Map<String, Object>> getFbCard(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFbCard", paramMap);
		return list;
	}
		
	
}
