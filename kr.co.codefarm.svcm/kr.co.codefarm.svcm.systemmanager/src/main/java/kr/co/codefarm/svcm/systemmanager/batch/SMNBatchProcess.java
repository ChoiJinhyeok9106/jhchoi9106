package kr.co.codefarm.svcm.systemmanager.batch;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SMNBatchProcess {
	
	@Autowired
	private ICommonMainDao commonDao;
	
	@Value("${spring.databse.main}")
	private String databaseMain;
	
	@Value("${spring.profiles}")
	private String profiles;	
	
	private String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.systemmanager.{databse.main}.batch";
	
	@Scheduled(cron = "0 0 01 * * ?") //매일 새벽1시
	public void coreCompetenceJob() {
		// 현재 사용하지 않는기능 2022-05-06 wjjoo
//		System.out.println("########################### 사용자 정보 이관 START ##############################");
//		commonDao.selectList(DEFAULT_MAPPER_PATH + ".SP_SMN_USER_V_UPDATE_BATCH");
//		System.out.println("########################### 사용자 정보 이관 END ##############################");
	}	
}
