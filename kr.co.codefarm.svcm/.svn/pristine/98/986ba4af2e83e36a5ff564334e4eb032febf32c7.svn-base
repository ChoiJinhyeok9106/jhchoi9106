/**
 * 
 */
package kr.co.codefarm.svcm.commons.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.codefarm.svcm.commons.api.manager.ICommonAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Dki_s
 *
 */
public class ManagedApiBeanMap {
	
	private static Map<String, ICommonAPI> innerMap = new HashMap<>();
	
	public static ICommonAPI get(String key) {
		return innerMap.get(key);
	}
	
	public static void put(String key, ICommonAPI value) {
		innerMap.put(key, value);
	}
	
	public static ICommonAPI remove(String key) {
		return innerMap.remove(key);
	}
	
	public static boolean containsKey(String key) {
		return innerMap.containsKey(key);
	}
	
	public static boolean containsValue(ICommonAPI value) {
		return innerMap.containsValue(value);
	}
	
	public static void clear() {
		innerMap.clear();
	}
	
	public static Set<Entry<String, ICommonAPI>> entrySet() {
		return innerMap.entrySet();
	}
	
	public static Set<String> keySet() {
		return innerMap.keySet();
	}
	
	public static boolean isEmpty() {
		return innerMap.isEmpty();
	}
	
	public static void putAll(Map<? extends String, ? extends ICommonAPI> m) {
		innerMap.putAll(m);
	}
	
	public static Map<String, ? extends ICommonAPI> getMap() {
		return innerMap;
	}
	
	public static String toJSONString() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(innerMap);
	}

}
