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
public class FarmBoardService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.additional.{databse.main}.farmboard";

	//팜보드 초대 카운트
	public List<Map<String, Object>> getInvitedCnt(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getInvitedCnt", paramMap);
		return list;
	}
	
	//팜보드 메인
	public List<Map<String, Object>> getFarmBoardMainList(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardMainList", paramMap);
		return list;
	}

	public Map<String, Object> insertFarmBoard(Map<String, Object> pFarmboardRow) {
		Map<String, Object> insResult = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertFarmBoard", pFarmboardRow);
		return insResult;
	}
	
	public int updateFarmBoard(Map<String, Object> pFarmboardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateFarmBoard", pFarmboardRow);
		return updCnt;
	}
	
	public int deleteFarmBoard(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteFarmBoard", paramMap);
		return delCnt;
	}
	
	//라벨
	public List<Map<String, Object>> getLabelList(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getLabelList", paramMap);
		return list;
	}
	
	public int insertLabel(Map<String, Object> pLabelRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertLabel", pLabelRow);
		return insCnt;
	}
	
	public int deleteLabel(Map<String, Object> pLabelRow) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteLabel", pLabelRow);
		return delCnt;
	}
	
	//팜포드 참여자
	public List<Map<String, Object>> getFarmBoardChamyeojaList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardChamyeojaList", paramMap);
		
		return list;
	}

	public int insertFarmBoardChamyeoja(Map<String, Object> pFarmboardRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertFarmBoardChamyeoja", pFarmboardRow);
		return insCnt;
	}
	
	public int updateFarmBoardChamyeoja(Map<String, Object> pFarmboardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateFarmBoardChamyeoja", pFarmboardRow);
		return updCnt;
	}
	
	public int updateBookmark(Map<String, Object> pFarmboardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBookmark", pFarmboardRow);
		return updCnt;
	}
	
	public int updateAnswInvitiation(Map<String, Object> pFarmboardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAnswInvitiation", pFarmboardRow);
		return updCnt;
	}
	
	public int deleteFarmBoardChamyeoja(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteFarmBoardChamyeoja", paramMap);
		return delCnt;
	}
	
	//팜포드 리스트
	public List<Map<String, Object>> getFarmBoardList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardList", paramMap);
		
		return list;
	}
	
	public Map<String, Object> insertFarmBoardList(Map<String, Object> pFarmboardRow) {
		Map<String, Object> insResult = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertFarmBoardList", pFarmboardRow);
		return insResult;
	}
	
	public int updateFarmBoardList(Map<String, Object> pFarmboardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateFarmBoardList", pFarmboardRow);
		return updCnt;
	}
	
	public int deleteFarmBoardList(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteFarmBoardList", paramMap);
		return delCnt;
	}
	
	//팜포드 리스트 참여자
	public List<Map<String, Object>> getFarmBoardListChamyeojaList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardListChamyeojaList", paramMap);
		
		return list;
	}
	
	public int insertFarmBoardListChamyeoja(Map<String, Object> pFarmboardRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertFarmBoardListChamyeoja", pFarmboardRow);
		return insCnt;
	}
	
	public int updateFarmBoardListChamyeoja(Map<String, Object> pFarmboardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateFarmBoardListChamyeoja", pFarmboardRow);
		return updCnt;
	}
	
	public int deleteFarmBoardListChamyeoja(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteFarmBoardListChamyeoja", paramMap);
		return delCnt;
	}
	
	//팜포드 카드
	public List<Map<String, Object>> getFarmBoardCardList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardCardList", paramMap);
		
		return list;
	}
	
	public List<Map<String, Object>> getFarmBoardCardLabelList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardCardLabelList", paramMap);
		
		return list;
	}
	
	public Map<String, Object> insertFarmBoardCard(Map<String, Object> pFarmboardRow) {
		Map<String, Object> insResult = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertFarmBoardCard", pFarmboardRow);
		return insResult;
	}
	
	public int updateFarmBoardCard(Map<String, Object> pFarmboardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateFarmBoardCard", pFarmboardRow);
		return updCnt;
	}
	
	public int deleteFarmBoardCard(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteFarmBoardCard", paramMap);
		return delCnt;
	}
	
	//팜포드 카드 참여자
	public List<Map<String, Object>> getFarmBoardCardChamyeojaList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardCardChamyeojaList", paramMap);
		
		return list;
	}
	
	public int insertFarmBoardCardChamyeoja(Map<String, Object> pFarmboardRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertFarmBoardCardChamyeoja", pFarmboardRow);
		return insCnt;
	}
	
	public int deleteFarmBoardCardChamyeoja(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteFarmBoardCardChamyeoja", paramMap);
		return delCnt;
	}
	
	//파일 키 업데이트
	public int updateAddCardDetailFile(Map<String, Object> paramMap) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateAddCardDetailFile", paramMap);
		return updCnt;
	}
		
	//팜포드 카드 상세
	public List<Map<String, Object>> getFarmBoardCardDetailList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardCardDetailList", paramMap);
		
		return list;
	}
	
	public Map<String, Object> insertFarmBoardCardDetail(Map<String, Object> pFarmboardRow) {
		Map<String, Object> insResult = commonDao.insertReturnData(DEFAULT_MAPPER_PATH + ".insertFarmBoardCardDetail", pFarmboardRow);
		return insResult;
	}
	
	public int updateFarmBoardCardDaepyoImg(Map<String, Object> pFarmboardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateFarmBoardCardDaepyoImg", pFarmboardRow);
		return updCnt;
	}
	
	public int updateFarmBoardCardDetail(Map<String, Object> pFarmboardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateFarmBoardCardDetail", pFarmboardRow);
		return updCnt;
	}
	
	public int deleteFarmBoardCardDetail(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteFarmBoardCardDetail", paramMap);
		return delCnt;
	}
	
	//팜포드 카드 상세 항목
	public List<Map<String, Object>> getFarmBoardCardDetailItemList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFarmBoardCardDetailItemList", paramMap);
		
		return list;
	}

	public int insertFarmBoardCardDetailItem(Map<String, Object> pFarmboardRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertFarmBoardCardDetailItem", pFarmboardRow);
		return insCnt;
	}
	
	public int updateFarmBoardCardDetailItem(Map<String, Object> pFarmboardRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateFarmBoardCardDetailItem", pFarmboardRow);
		return updCnt;
	}
	
	public int deleteFarmBoardCardDetailItem(Map<String, Object> paramMap) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteFarmBoardCardDetailItem", paramMap);
		return delCnt;
	}
}
