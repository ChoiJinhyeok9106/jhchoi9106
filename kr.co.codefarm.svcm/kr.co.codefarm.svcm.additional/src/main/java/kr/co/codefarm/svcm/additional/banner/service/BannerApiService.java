/**
 * 
 */
package kr.co.codefarm.svcm.additional.banner.service;

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
public class BannerApiService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.additional.{databse.main}.bannerApi";

	public List<Map<String, Object>> getBannerPostList(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getBannerPostList", paramMap);
		return list;
	}
}
