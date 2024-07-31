/**
 * 
 */
package kr.co.codefarm.svcm.additional.banner.service;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@Service
public class BannerService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.additional.{databse.main}.banner";

	public List<Map<String, Object>> getBannerList(String pSearchType, String pSearchArgv, String pPageNo, String pRowCount, String pMenuGb) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		paramMap.put("pMenuGb", pMenuGb);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getBannerList", paramMap);
		return list;
	}
	
	public Object getBanner(String pBannerSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BANNER_SEQ", pBannerSeq);
		Object banner = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getBanner", paramMap);
		return banner;
	}
	
	public int deleteBanner(String pBannerSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BANNER_SEQ", pBannerSeq);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteBanner", paramMap);
		return delCnt;
	}
	
	public int updateBanner(Map<String, Object> pBannerRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBanner", pBannerRow);
		return updCnt;
	}
	
	public int insertBanner(Map<String, Object> pBannerRow) {
		Map<String, Object> paramMap = new HashMap<>();
		
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertBanner", pBannerRow);
		return insCnt;
	}

	public List<Map<String, Object>> getBannerGroupList(String pSearchType, String pSearchArgv, String pPageNo, String pRowCount) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("pSearchType", pSearchType);
		paramMap.put("pSearchArgv", pSearchArgv);
		paramMap.put("pPageNo", pPageNo);
		paramMap.put("pRowCount", pRowCount);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getBannerGroupList", paramMap);
		return list;
	}

	public Object getBannerGroup(String bannerGroupSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BANNER_GROUP_SEQ", bannerGroupSeq);
		Object banner = commonDao.selectObject(DEFAULT_MAPPER_PATH + ".getBannerGroup", paramMap);
		return banner;
	}

	public int insertBannerGroup(Map<String, Object> pBannerGroupRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertBannerGroup", pBannerGroupRow);
		return insCnt;
	}

	public int updateBannerGroup(Map<String, Object> pBannerGroupRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBannerGroup", pBannerGroupRow);
		return updCnt;
	}

	public int deleteBannerGroup(String bannerGroupSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BANNER_GROUP_SEQ", bannerGroupSeq);
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteBannerGroup", paramMap);
		return delCnt;
	}

	public List<Map<String, Object>> getBannerTotalGroupList() {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getTotalBannerGroupList");
		return list;
	}

	public List<Map<String, Object>> getBannerTotalPostList(String bannerGroupSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BANNER_GROUP_SEQ", bannerGroupSeq);
		
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getTotalBannerPostList", paramMap);
		return list;
	}

	public List<Map<String, Object>> getBannerTotalList() {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getTotalBannerList");
		return list;
	}

	public int insertBannerPost(Map<String, Object> paramMap) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertBannerPost", paramMap);
		return insCnt;
	}

	public int updateOrdBannerPost(Map<String, Object> paramMap) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateOrdBannerPost", paramMap);
		return updCnt;
	}

	public int deleteOrdBannerPost(String pBannerGroupSeq, String pBannerSeq, String pPostSeq) {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("BANNER_GROUP_SEQ", pBannerGroupSeq);
		paramMap.put("BANNER_SEQ", pBannerSeq);
		paramMap.put("POST_SEQ", pPostSeq);
		
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteBannerPost", paramMap);
		return delCnt;
	}

	public int updateBannerPost(Map<String, Object> pBannerPostRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateBannerPost", pBannerPostRow);
		return updCnt;
	}
}
