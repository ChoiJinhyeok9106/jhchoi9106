/**
 * 
 */
package kr.co.codefarm.svcm.commons.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @author Dki_s
 *
 */
public class ObjectUtil {
	
	public static Map<String, Object> convertObjectToMap(Object obj) {
		Map<String, Object> retMap = new HashMap<>();
		
		for (Field field : obj.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				retMap.put(field.getName(), field.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return retMap;
	}
	
	public static Object convertMapToObject(Map<String, Object> map, Object obj) {
		for (String key : map.keySet()) {
			try {
				Field field = obj.getClass().getDeclaredField(key);
				field.setAccessible(true);
				field.set(obj, String.valueOf(map.get(key)));
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return obj;
	}
	
	//kmp: List<Map<String, Object>> -> Json String
	public static String listMapToJsonString(List<Map<String, Object>> list)
	{       
	    JSONArray jsonArr = new JSONArray();
	    for(Map<String, Object> map : list) 
	    {
	        JSONObject jsonObj = new JSONObject();
	        for(Map.Entry<String, Object> entry : map.entrySet()) 
	        {
	            String key = entry.getKey();
	            Object value = entry.getValue();
	            try 
	            {
	            	jsonObj.put(key, value);
	            } catch (JSONException e) 
	            {
	                e.printStackTrace();
	            }                           
	        }
	        jsonArr.put(jsonObj);
	    }
	    return jsonArr.toString();
	}

}
