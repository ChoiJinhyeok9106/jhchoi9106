/**
 * 
 */
package kr.co.codefarm.svcm.commons.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.codefarm.svcm.commons.api.dto.AuthDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Dki_s
 *
 */
public class AuthMap {
	
	private static Map<String, List<AuthDto>> innerMap = new HashMap<>();
	
	public static List<AuthDto> get(String key) {
		return innerMap.get(key);
	}
	
	public static void put(String key, List<AuthDto> value) {
		innerMap.put(key, value);
	}
	
	public static List<AuthDto> remove(String key) {
		return innerMap.remove(key);
	}
	
	public static boolean containsKey(String key) {
		return innerMap.containsKey(key);
	}
	
	public static boolean containsValue(Object value) {
		return innerMap.containsValue(value);
	}
	
	public static void clear() {
		innerMap.clear();
	}
	
	public static Set<String> keySet() {
		return innerMap.keySet();
	}
	
	public static boolean isEmpty() {
		return innerMap.isEmpty();
	}
	
	public static Map<String, List<AuthDto>> getMap() {
		return innerMap;
	}
	
	public static String toJSONString() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(innerMap);
	}

}
