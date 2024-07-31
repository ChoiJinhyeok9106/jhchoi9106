package kr.co.codefarm.svcm.commons.component;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import kr.co.codefarm.svcm.commons.map.DefaultInfoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SmsComponent {
	
	@Autowired
	private ICommonMainDao commonMainDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.commons.{databse.main}.sms";
	
	public int sendSms(String[] recvNumbers, String sendMsg, String sendNumber) {
		int sendCnt                  = 0;
		Map<String, Object> paramMap = new HashMap<>();
		
		if (sendNumber == null || "".equals(sendNumber)) {
			sendNumber = DefaultInfoMap.get("SMS_SEND_NO").toString();
		}
		
		paramMap.put("SEND_NUMBER", sendNumber);
		paramMap.put("SEND_MSG", sendMsg);
		
		for (int idx = 0; idx < recvNumbers.length; idx++) {
			paramMap.put("RECV_NUMBER", recvNumbers[idx]);
			sendCnt += this.commonMainDao.insert(DEFAULT_MAPPER_PATH + ".INSERT_SMS", paramMap);
		}
		
		return sendCnt;
	}
	
	public int sendSms(String[] recvNumbers, String sendMsg) {
		return this.sendSms(recvNumbers, sendMsg, null);
	}
	
	public int sendSms(String recvNumber, String sendMsg, String sendNumber) {
		String[] recvNumbers = {recvNumber};
		return this.sendSms(recvNumbers, sendMsg, sendNumber);
	}
	
	public int sendSms(String recvNumber, String sendMsg) {
		String[] recvNumbers = {recvNumber};
		return this.sendSms(recvNumbers, sendMsg, null);
	}

}
