/**
 * 
 */
package kr.co.codefarm.svcm.commons.api.manager;

import kr.co.codefarm.svcm.commons.annotation.*;
import kr.co.codefarm.svcm.commons.api.dto.AlarmDto;
import kr.co.codefarm.svcm.commons.api.dto.AuthDto;
import kr.co.codefarm.svcm.commons.api.dto.DefaultInfoDto;
import kr.co.codefarm.svcm.commons.api.dto.ServiceDto;
import kr.co.codefarm.svcm.commons.map.*;
import kr.co.codefarm.svcm.commons.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Dki_s
 *
 */
@Slf4j
@Component
public class ManagedApiCollector {
	
	@Autowired
	private ApplicationContext context;
	
	public ManagedApiCollector(ApplicationContext context) {
		this.context = context;
	}
	
	public int setManagedApiBeanMap() {
		ManagedApiBeanMap.clear();
		
		Map<String, Object> beans = this.context.getBeansWithAnnotation(ManagedAPI.class);
		log.info("-- ManagedApiBeanMap Setting Start --");
		
		for (String key : beans.keySet()) {
	    	Object bean      = beans.get(key);
	    	String serviceNm = String.valueOf(AnnotationUtils.getValue(bean.getClass().getAnnotation(ManagedAPI.class), "service"));
	    	
	    	if (!"".equals(serviceNm) && !ManagedApiBeanMap.containsKey(serviceNm) && ICommonAPI.class.isAssignableFrom(bean.getClass())) {
    			ManagedApiBeanMap.put(serviceNm, (ICommonAPI)bean);
    			log.info("-- API Service : " + serviceNm);
    		} else if (!"".equals(serviceNm)) {
    			if (ManagedApiBeanMap.containsKey(serviceNm)) {
    	    		log.error("### Service Name Duplicated. ###");
    	    	} else if (!ICommonAPI.class.isAssignableFrom(bean.getClass())) {
    	    		log.error("### Service Class Type Not Matched. ###");
    	    	}
    			
    			ManagedApiBeanMap.clear();
    			break;
    		}
		}	
		log.info("-- ManagedApiBeanMap Setting Complete --");
		
		return ManagedApiBeanMap.getMap().size();
	}
	
	@SuppressWarnings("unchecked")
	public int setServiceMap() {
		ServiceMap.clear();
		
		Map<String, Object> beans = this.context.getBeansWithAnnotation(ManagedAPI.class);
		log.info("-- ServiceMap Setting Start --");
		
		for (String key : beans.keySet()) {
	    	Object bean = beans.get(key);
	    	
	    	for (Method method : bean.getClass().getDeclaredMethods()) {
	            if (method.getAnnotation(ServicesRelay.class) != null) {
	            	Type returnType = method.getGenericReturnType();
	            	
	            	if (returnType instanceof ParameterizedType) {
	            		ParameterizedType type = (ParameterizedType) returnType;
	            		Type[] typeArguments   = type.getActualTypeArguments();
	            		
	            		if (typeArguments.length == 1) {
	            	        if (List.class.equals(method.getReturnType()) && ServiceDto.class.equals(typeArguments[0])) {
	            	        	try {
	        						List<ServiceDto> list = (List<ServiceDto>) method.invoke(bean);
	        						for (ServiceDto item : list) {
	        							ServiceMap.put(item.getSERVICE_CD(), ObjectUtil.convertObjectToMap(item));
	        		    			}
	        					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	        						log.error("### Service collection failed. ###");
	        						log.error(e.getMessage());
	        					}
	            	        } else {
	            	        	log.info("### Return type of Services Relay is List<ServiceDto> ###");
	            	        }
	            		} else {
	            			log.info("### Return type of Services Relay is List<ServiceDto> ###");
	            		}
	            	} else {
	            		log.info("### Return type of Services Relay is List<ServiceDto> ###");
	            	}
	            }
	        }
		}	
		log.info("-- ServiceMap Setting Complete --");
		
		return ServiceMap.getMap().size();
	}
	
	@SuppressWarnings("unchecked")
	public int setAuthMap() {
		AuthMap.clear();
		
		Map<String, Object> beans = this.context.getBeansWithAnnotation(ManagedAPI.class);
		log.info("-- AuthMap Setting Start --");
		
		for (String key : beans.keySet()) {
	    	Object bean = beans.get(key);
	    	
	    	for (Method method : bean.getClass().getDeclaredMethods()) {
	            if (method.getAnnotation(AuthRelay.class) != null) {
	            	Type returnType = method.getGenericReturnType();
	            	
	            	if (returnType instanceof ParameterizedType) {
	            		ParameterizedType type = (ParameterizedType) returnType;
	            		Type[] typeArguments   = type.getActualTypeArguments();
	            		
	            		if (typeArguments.length == 1) {
	            	        if (List.class.equals(method.getReturnType()) && AuthDto.class.equals(typeArguments[0])) {
	            	        	try {
	        						List<AuthDto> list     = (List<AuthDto>) method.invoke(bean);
	        						List<AuthDto> authList = new ArrayList<>();
	        						String authCd          = "";
	        						
	        						for (AuthDto item : list) {
	        							if (!"".equals(authCd) && !authCd.equals(item.getAUTH_CD())) {
	        								AuthMap.put(authCd, authList);
	        								authList = new ArrayList<>();
	        							}
	        							authList.add(item);
	        							authCd = item.getAUTH_CD();
	        		    			}
	        						AuthMap.put(authCd, authList);
	        					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	        						e.printStackTrace();
	        						log.error("### Auth collection failed. ###");
	        						log.error(e.getMessage());
	        					}
	            	        } else {
	            	        	log.info("### Return type of Auth Relay is List<AuthDto> ###");
	            	        }
	            		} else {
	            			log.info("### Return type of Auth Relay is List<AuthDto> ###");
	            		}
	            	} else {
	            		log.info("### Return type of Auth Relay is List<AuthDto> ###");
	            	}
	            }
	        }
		}	
		log.info("-- AuthMap Setting Complete --");
		
		return AuthMap.getMap().size();
	}
	
	@SuppressWarnings("unchecked")
	public int setAlarmMap() {
		AlarmMap.clear();
		
		Map<String, Object> beans = this.context.getBeansWithAnnotation(ManagedAPI.class);
		log.info("-- AlarmMap Setting Start --");
		
		for (String key : beans.keySet()) {
	    	Object bean = beans.get(key);
	    	
	    	for (Method method : bean.getClass().getDeclaredMethods()) {
	    		if ("alarmTest".equals(method.getName())) {
	    			Annotation[] annotation = method.getAnnotations();
	    			for (int idx = 0; idx < annotation.length; idx++) {
	    			}
	    		}
	    		if ("getAllAlarmList".equals(method.getName())) {
	    			Annotation[] annotation = method.getAnnotations();
	    			for (int idx = 0; idx < annotation.length; idx++) {
	    			}
	    		}
	    		
	            if (method.getAnnotation(AlarmRelay.class) != null && method.getAnnotation(AlarmRelay.class).type().compareTo(AlarmType.COLLECTOR) == 0) { 
	            	Type returnType = method.getGenericReturnType();
	            	if (returnType instanceof ParameterizedType) {
	            		ParameterizedType type = (ParameterizedType) returnType;
	            		Type[] typeArguments   = type.getActualTypeArguments();
	            		
	            		if (typeArguments.length == 1) {
	            	        if (List.class.equals(method.getReturnType()) && AlarmDto.class.equals(typeArguments[0])) {
	            	        	try {
	        						List<AlarmDto> list = (List<AlarmDto>) method.invoke(bean);
	        						for (AlarmDto item : list) {
	        							AlarmMap.put(item.getALARM_SEQ(), item);
	        		    			}
	        					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	        						log.error("### Alarm collection failed. ###");
	        						log.error(e.getMessage());
	        					}
	            	        } else {
	            	        	log.info("### Return type of Alarm Relay is List<AlarmDto> ###");
	            	        }
	            		} else {
	            			log.info("### Return type of Alarm Relay is List<AlarmDto> ###");
	            		}
	            	} else {
	            		log.info("### Return type of Alarm Relay is List<AlarmDto> ###");
	            	}
	            }
	        }
		}	
		log.info("-- AlarmMap Setting Complete --");
		
		return AlarmMap.getMap().size();
	}
	
	public int setDefaultInfoMap() {
		DefaultInfoMap.clear();
		
		Map<String, Object> beans = this.context.getBeansWithAnnotation(ManagedAPI.class);
		log.info("-- DefaultInfoMap Setting Start --");
		
		for (String key : beans.keySet()) {
	    	Object bean = beans.get(key);
	    	
	    	for (Method method : bean.getClass().getDeclaredMethods()) {
	            if (method.getAnnotation(DefaultInfoRelay.class) != null) {
	            	if (DefaultInfoDto.class.equals(method.getReturnType())) {
        	        	try {
        	        		DefaultInfoDto defaultInfoDto = (DefaultInfoDto) method.invoke(bean);
        	        		DefaultInfoMap.getMap().putAll(ObjectUtil.convertObjectToMap(defaultInfoDto));
    					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
    						log.error("### Service collection failed. ###");
    						log.error(e.getMessage());
    					}
        	        } else {
        	        	log.info("### Return type of DefaultInfoRelay is DefaultInfoDto ###");
        	        }
	            }
	        }
		}	
		log.info("-- DefaultInfoMap Setting Complete --");
		
		return DefaultInfoMap.getMap().size() > 0 ? 1:0;
	}

}
