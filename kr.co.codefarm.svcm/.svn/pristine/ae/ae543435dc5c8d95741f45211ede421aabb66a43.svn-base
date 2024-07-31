/**
 * 
 */
package kr.co.codefarm.svcm.commons.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Dki_s
 *
 */
public class ParameterMap {
	
	private Map<String, Object> innerMap = new HashMap<>();
	
	public Object get(String key) {
		return this.innerMap.get(key);
	}
	
	public void put(String key, Object value) {
		this.innerMap.put(key, value);
	}
	
	public Object remove(String key) {
		return this.innerMap.remove(key);
	}
	
	public boolean containsKey(String key) {
		return this.innerMap.containsKey(key);
	}
	
	public boolean containsValue(Object value) {
		return this.innerMap.containsValue(value);
	}
	
	public void clear() {
		this.innerMap.clear();
	}
	
	public Set<Entry<String, Object>> entrySet() {
		return this.innerMap.entrySet();
	}
	
	public Set<String> keySet() {
		return this.innerMap.keySet();
	}
	
	public boolean isEmpty() {
		return this.innerMap.isEmpty();
	}
	
	public void putAll(Map<? extends String, ? extends Object> m) {
		this.innerMap.putAll(m);
	}
	
	public Map<String, Object> getMap() {
		return this.innerMap;
	}
	
	public String toJSONString() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(this.innerMap);
	}
	
	public String toString() {
		return this.innerMap.toString();
	}
	
	/* 이주데이타 최진혁 수정 */

    public void setMap(Map<String, Object> map) {
        this.innerMap = map;
    }

}
