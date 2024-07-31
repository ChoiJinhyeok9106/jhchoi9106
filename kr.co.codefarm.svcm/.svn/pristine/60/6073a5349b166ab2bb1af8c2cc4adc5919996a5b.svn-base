package kr.co.codefarm.svcm.commons.interceptor;

import kr.co.codefarm.svcm.commons.annotation.AlarmRelay;
import kr.co.codefarm.svcm.commons.annotation.AlarmType;
import kr.co.codefarm.svcm.commons.annotation.ManagedAPI;
import kr.co.codefarm.svcm.commons.api.dto.AlarmDto;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.AlarmMap;
import kr.co.codefarm.svcm.commons.utils.EtcUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AlarmInterceptor implements HandlerInterceptor {
	
	@Autowired
	private ApplicationContext context;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		List<Object> dataParamList = new ArrayList<>();
		List<AlarmDto> alarmList   = new ArrayList<>();
		
		this.payloadSetting(request, dataParamList);
		this.parameterMapSetting(request, dataParamList);
		
		for (String key : AlarmMap.keySet()) {
			if (AlarmMap.get(key).getALARM_CHK_GB().equals("01") && request.getMethod().equals(AlarmMap.get(key).getMETHOD_GB()) && request.getRequestURI().startsWith(AlarmMap.get(key).getAPI_URL())) {
				log.info("## IN PRE HANDLER ## : [" + request.getMethod() + "] " + request.getRequestURI());
				alarmList.add(AlarmMap.get(key));
			}
		}

		methodInvocation(alarmList, dataParamList, request);
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		List<Object> dataParamList       = new ArrayList<>();
		List<AlarmDto> alarmList         = new ArrayList<>();
		
		this.payloadSetting(request, dataParamList);
		this.parameterMapSetting(request, dataParamList);
		this.defaultValueSetting(request, dataParamList);

		for (String key : AlarmMap.keySet()) {
			 if (    AlarmMap.get(key).getALARM_CHK_GB().equals("02")
				  && request.getMethod().equals(AlarmMap.get(key).getMETHOD_GB())
				  && request.getRequestURI().equals(AlarmMap.get(key).getAPI_URL())
				 ) {
				 if(AlarmMap.get(key).getAPI_URL().equals("/additional/board/post") && request.getMethod().equals("PUT")){
					 if(((Map<String, Object>) dataParamList.get(0)).get("GONGJI_YN").equals("Y")){
						log.info("## IN POST HANDLER ## : [" + request.getMethod() + "] " + request.getRequestURI());
						alarmList.add(AlarmMap.get(key));
					 }
				 }else{
					log.info("## IN POST HANDLER ## : [" + request.getMethod() + "] " + request.getRequestURI());
					alarmList.add(AlarmMap.get(key));
				 }
			}
		}

		this.methodInvocation(alarmList, dataParamList, request);
	}
	
	private void methodInvocation(List<AlarmDto> alarmList, List<Object> dataParamList, HttpServletRequest request) {
		if (alarmList.size() > 0) {
			Map<String, Object> beans = this.context.getBeansWithAnnotation(ManagedAPI.class);
			
			for (String key : beans.keySet()) {
				Object bean = beans.get(key);
				for (Method method : bean.getClass().getDeclaredMethods()) {
					if (method.getAnnotation(AlarmRelay.class) != null && method.getAnnotation(AlarmRelay.class).type().compareTo(AlarmType.COLLECTOR) != 0) {
						for (AlarmDto alarmDto : alarmList) {
							if (("01".equals(alarmDto.getALARM_GB()) && method.getAnnotation(AlarmRelay.class).type().compareTo(AlarmType.ALARM) == 0) ||
									("02".equals(alarmDto.getALARM_GB()) && method.getAnnotation(AlarmRelay.class).type().compareTo(AlarmType.LOG) == 0) ||
									("03".equals(alarmDto.getALARM_GB()) && method.getAnnotation(AlarmRelay.class).type().compareTo(AlarmType.USER_RESTRICTIONS) == 0)) {
								new Thread(new Runnable() {
									public void run() {
										try {
											method.invoke(bean, alarmDto, dataParamList, request);
										} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
											e.printStackTrace();
										}
										return;
									}
								}).start();
							}
						}
					}
				}
			}
		}
	}
	
	private void payloadSetting(HttpServletRequest request, List<Object> dataParamList) throws IOException {
		if (request.getContentType() != null && request.getContentType().toLowerCase().startsWith("application/json")) {
			StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader reader       = request.getReader();
		    try {
		        String line;
		        while ((line = reader.readLine()) != null) {
		        	line = line.replace("\\n", System.getProperty("line.separator"));
		        	line = line.replace("\\r", "");
		        	stringBuilder.append(line).append('\n');
		        }
		        
		        JsonParser jsonParser = new BasicJsonParser();
		        String jsonString     = stringBuilder.toString();
		        
		        if (jsonString.startsWith("[")) {
		        	dataParamList = jsonParser.parseList(jsonString);
		        } else if (jsonString.startsWith("{")) {
		        	Map<String, Object> payload = jsonParser.parseMap(jsonString);
		        	dataParamList.add(payload);
		        }
		    } catch(IOException e) {
				log.error("Non-JSON String");
			} finally {
		        reader.close();
		    }
		}
	}
	
	private void parameterMapSetting(HttpServletRequest request, List<Object> dataParamList) throws IOException {
		Map<String, Object> dataParamMap = new HashMap<>();
		Map<String, String[]> requestMap = request.getParameterMap();
		
        for (String key : requestMap.keySet()) {
            String[] values        = requestMap.get(key);
            String parameterValue  = "";

            if (values.length == 1) {
                parameterValue = values[0];
            } else {
                for (int idx = 0; idx < values.length; idx++) {
                    String listValue = values[idx];
                    parameterValue += "," + listValue;
                }
                parameterValue = parameterValue.substring(1);
            }

            String value = parameterValue;
            dataParamMap.put(key, value);
        }        
        dataParamList.add(dataParamMap);
	}
	
	private void defaultValueSetting(HttpServletRequest request, List<Object> dataParamList) {
		Map<String, Object> dataParamMap = new HashMap<>();
		
		String ip = EtcUtil.getIP(request);
		String pg = request.getServletPath();
		String id = "";
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        if (principal instanceof ScvmUserDetailsImpl) {
	        	id = ((ScvmUserDetailsImpl) principal).getUserId();
	        }
	    }
		
	    dataParamMap.put("SYSTEM_ID",  id);
	    dataParamMap.put("SYSTEM_IP",  ip);
	    dataParamMap.put("SYSTEM_PGM", pg);
        
        dataParamList.add(dataParamMap);
	}

}
