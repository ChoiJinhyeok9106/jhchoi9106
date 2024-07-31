/**
 * 
 */
package kr.co.codefarm.svcm.systemmanager.linkplus.service;

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
public class LinkplusService {
	@Autowired
	private ICommonMainDao commonDao;
	
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.linkplus";
	

	public List<Map<String, Object>> getLinkplusList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getLinkplusList", paramMap);
		return list;
	}
	
	public int insertLinkplus(Map<String, Object> pLinkplusRow) {
		int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertLinkplus", pLinkplusRow);
		return insCnt;
	}
	
	public int updateLinkplus(Map<String, Object> pefaultInfoRow) {
		int updCnt = commonDao.update(DEFAULT_MAPPER_PATH + ".updateLinkplus", pefaultInfoRow);
		return updCnt;
	}
	
	public int deleteLinkplus(Map<String, Object> pLinkplusRow) {
		int delCnt = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteLinkplus", pLinkplusRow);
		return delCnt;
	}
}
