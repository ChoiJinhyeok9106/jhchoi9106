/**
 * 
 */
package kr.co.codefarm.svcm.commons.listener;

import kr.co.codefarm.svcm.commons.api.manager.ManagedApiCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Dki_s
 *
 */
@Component
@Slf4j
public class SvcmWebMvcEventListener {
	
	@Autowired
	private ManagedApiCollector managedApiCollector;
	
	@EventListener
	public void handleContextRefreshEvent(ApplicationStartedEvent ctxStartEvt) {
		int managedApiBeanMapCnt = managedApiCollector.setManagedApiBeanMap();
		int serviceMapCnt        = managedApiCollector.setServiceMap();
		int authMapCnt           = managedApiCollector.setAuthMap();
		int alarmMapCnt          = managedApiCollector.setAlarmMap();
		int defaultInfoMapCnt    = managedApiCollector.setDefaultInfoMap();
		
		log.info("//----> ManagedAPI collection (" + managedApiBeanMapCnt + ")");
		log.info("//----> Service collection (" + serviceMapCnt + ")");
		log.info("//----> Auth collection (" + authMapCnt + ")");
		log.info("//----> Alarm collection (" + alarmMapCnt + ")");
		log.info("//----> DefaultInfo collection (" + defaultInfoMapCnt + ")");
	}
	
}
