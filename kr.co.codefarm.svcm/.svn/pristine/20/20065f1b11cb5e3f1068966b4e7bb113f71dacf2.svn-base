
 /**
 *
 */
package kr.co.codefarm.svcm.commons.dao.impl;

 import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dki_s
 *
 */
@Repository
@Slf4j
public class CommonMainDaoImpl implements ICommonMainDao {
    
    @Value("${spring.databse.main}")
    private String dbTemplateName;
    
    private SqlSession sqlSession;
    
    @Autowired
    public void setSqlSession(ApplicationContext context) {
        this.sqlSession = (SqlSession) context.getBean(dbTemplateName + "SessionTemplate");
    }
    
    private String convClobToString(Clob clob) {
        StringBuffer strOut = new StringBuffer();
        String strClob = "";

        try {
            BufferedReader br = new BufferedReader(clob.getCharacterStream());
            while ((strClob = br.readLine()) != null) {
                strOut.append(strClob);
                strOut.append(System.getProperty("line.separator"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strOut.toString();
    }
    
    //postgresql사용을 위해 select시 모든 테이블 컬럼명을 대문자로 변환 20230910 jwson
    @Override
    public List<Map<String, Object>> selectList(String sqlMap, Map<String, Object> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - selectList (" + mapId + ")");
        
        List<Map<String, Object>> objList = this.sqlSession.selectList(mapId, data);
        
        for (Map<String, Object> map : objList) {
            Iterator<Entry<String, Object>> iter = map.entrySet().iterator();

            Map<String, Object> newMap = new HashMap<>();
            while (iter.hasNext()) {
                Entry<String, Object> entry = iter.next();
                String newKey = entry.getKey(); 
                Object value = entry.getValue();
                
                if (value != null && value instanceof Clob) {
                    value = convClobToString((Clob) value);
                }
                if (value == null) {
                    value = "";
                }
                
                newMap.put(newKey, value); 
            }
            
            map.clear();
            map.putAll(newMap);
        }
        return objList;
    }

    @Override
    public List<Map<String, Object>> selectList(String sqlMap) {
        return this.selectList(sqlMap, null);
    }
    
    //postgresql사용을 위해 select시 모든 테이블 컬럼명을 대문자로 변환 20230910 jwson
    @Override
    @SuppressWarnings("unchecked")
    public Object selectObject(String sqlMap, Map<String, Object> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - selectObject (" + mapId + ")");
        log.debug(this.sqlSession.getConnection().toString());

        Object obj = this.sqlSession.selectOne(mapId, data);

        if (obj instanceof Map) {
            Map<String, Object> resultMap = new HashMap<>();
            Iterator<Entry<String, Object>> iter = ((Map<String, Object>) obj).entrySet().iterator();

            while (iter.hasNext()) {
                Entry<String, Object> entry = iter.next();
                String newKey = entry.getKey();
                Object value = entry.getValue();

                if (value != null && value instanceof Clob) {
                    value = convClobToString((Clob) value);
                }
                if (value == null) {
                    value = "";
                }

                resultMap.put(newKey, value);
            }

            obj = resultMap;
        } else if (obj instanceof Clob) {
            obj = convClobToString((Clob) obj);
        }

        return obj;
    }

    @Override
    public Object selectObject(String mapId) {
        return this.selectObject(mapId, null);
    }

    @Override
    public int insert(String sqlMap, List<Map<String, Object>> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - insert (" + mapId + ")");
        
        int result = 0;

        for (Map<String, Object> map : data) {
            result += this.insert(mapId, map);
        }

        return result;
    }

    @Override
    public int insert(String sqlMap, Map<String, Object> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - insert (" + mapId + ")");
        
        return (Integer) this.sqlSession.insert(mapId, data);
    }

    @Override
    public int insert(String sqlMap) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - insert (" + mapId + ")");
        
        return (Integer) this.sqlSession.insert(mapId);
    }
    
    @Override
    public List<Map<String, Object>> insertReturnData(String sqlMap, List<Map<String, Object>> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - insertReturnKey (" + mapId + ")");
        
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> map : data) {
            int insertCnt = this.insert(mapId, map);
            map.put("_INSERT_ROW_CNT", insertCnt);
            result.add(map);
        }

        return result;
    }

    @Override
    public Map<String, Object> insertReturnData(String sqlMap, Map<String, Object> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - insertReturnKey (" + mapId + ")");
        int insertCnt = this.sqlSession.insert(mapId, data);
        data.put("_INSERT_ROW_CNT", insertCnt);
        return data;
    }

    @Override
    public int update(String sqlMap, List<Map<String, Object>> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - update (" + mapId + ")");
        
        int result = 0;

        for (Map<String, Object> map : data) {
            result += this.update(mapId, map);
        }

        return result;
    }

    @Override
    public int update(String sqlMap, Map<String, Object> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - update (" + mapId + ")");
        
        return (Integer) this.sqlSession.update(mapId, data);
    }

    @Override
    public int update(String sqlMap) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - update (" + mapId + ")");
        
        return (Integer) this.sqlSession.update(mapId);
    }

    @Override
    public int delete(String sqlMap, List<Map<String, Object>> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - delete (" + mapId + ")");
        
        int result = 0;

        for (Map<String, Object> map : data) {
            result += this.delete(mapId, map);
        }

        return result;
    }

    @Override
    public int delete(String sqlMap, Map<String, Object> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - delete (" + mapId + ")");
        
        return (Integer) this.sqlSession.delete(mapId, data);
    }

    @Override
    public int delete(String sqlMap) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - delete (" + mapId + ")");
        
        return (Integer) this.sqlSession.delete(mapId);
    }

    @Override
    public List<?> selectObjectList(String sqlMap, Map<String, Object> data) {
        String mapId = sqlMap.replace("{databse.main}", dbTemplateName);
        log.debug("//--> CDF DAO - selectObjectList (" + mapId + ")");
        return this.sqlSession.selectList(mapId, data);
    }

    @Override
    public List<?> selectObjectList(String mapId) {
        return this.selectObjectList(mapId, null);
    }

}

