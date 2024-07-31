/**
 * 
 */
package kr.co.codefarm.svcm.commons.dao;

import java.util.List;
import java.util.Map;

/**
 * @author Dki_s
 *
 */
public interface ICommonMainDao {
	
	public List<Map<String, Object>> selectList(String mapId, Map<String, Object> data);
	public List<Map<String, Object>> selectList(String mapId);
	
	public List<?> selectObjectList(String mapId, Map<String, Object> data);
	public List<?> selectObjectList(String mapId);

	public Object selectObject(String mapId, Map<String, Object> data);
	public Object selectObject(String mapId);

	public int insert(String mapId, List<Map<String, Object>> data);
	public int insert(String mapId, Map<String, Object> data);
	public int insert(String mapId);
	List<Map<String, Object>> insertReturnData(String mapId, List<Map<String, Object>> data);
	Map<String, Object> insertReturnData(String mapId, Map<String, Object> data);

	public int update(String mapId, List<Map<String, Object>> data);
	public int update(String mapId, Map<String, Object> data);
	public int update(String mapId);

	public int delete(String mapId, List<Map<String, Object>> data);
	public int delete(String mapId, Map<String, Object> data);
	public int delete(String mapId);

}
