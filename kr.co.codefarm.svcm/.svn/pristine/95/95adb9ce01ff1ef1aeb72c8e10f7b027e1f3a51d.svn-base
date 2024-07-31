/**
 * 
 */
package kr.co.codefarm.svcm.commons.service.file;

import kr.co.codefarm.svcm.commons.dao.ICommonMainDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PHS
 *
 */
@Service
@Slf4j
public class FileService {
	@Autowired
	private ICommonMainDao commonDao;
	private static String DEFAULT_MAPPER_PATH = "kr.co.codefarm.svcm.commons.{databse.main}.file";
	
	@Value("${sysprop.uploadDir}")
	private String UPLOAD_DIR;
	@Value("${homedir}")
	private String HOME_DIR;

	public int insertFile(Map<String, Object> pFileList) {
        int insCnt = commonDao.insert(DEFAULT_MAPPER_PATH + ".insertFile", pFileList);
        return insCnt;
    }
    
    public List<Map<String, Object>> getFileList(String pServiceAbbrNm, String pFileId, String pFileKey, String pFIleHangmok, String pFileNo) {
        Map<String, Object> paramMap = new HashMap<>();
        
        paramMap.put("SERVICE_ABBR_NM", pServiceAbbrNm);
        paramMap.put("FILE_ID", pFileId);
        paramMap.put("FILE_KEY", pFileKey);
        paramMap.put("FILE_HANGMOK", pFIleHangmok);
        paramMap.put("FILE_NO", pFileNo);
        
        List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFileList", paramMap);
        return list;
    }
    
    public int deleteFile(String pServiceAbbrNm, String pFileId, String pFileKey, String pFIleHangmok, String pFileNo) {
        Map<String, Object> paramMap = new HashMap<>();
        
        paramMap.put("SERVICE_ABBR_NM", pServiceAbbrNm);
        paramMap.put("FILE_ID", pFileId);
        paramMap.put("FILE_KEY", pFileKey);
        paramMap.put("FILE_HANGMOK", pFIleHangmok);
        paramMap.put("FILE_NO", pFileNo);
        
        List<Map<String, Object>> list = commonDao.selectList(DEFAULT_MAPPER_PATH + ".getFileList", paramMap);
        int delCnt                     = commonDao.delete(DEFAULT_MAPPER_PATH + ".deleteFile", paramMap);
        
        if(delCnt > 0) {
        	//TODO: 파일실제 삭제 처리 로직
    		int deleteCnt = 0;
    		
        	for (Map<String, Object> fileMap : list) {
        		String filePath = fileMap.get("FILE_PATH").toString();
        		String fullPath  = this.HOME_DIR + "/" + this.UPLOAD_DIR + filePath;
        		
        		fullPath = fullPath.replace("\\", File.separator).replace("/", File.separator);
        		
        		File file = new File(fullPath);
        		
        		if (file.isFile()) {
            		if (file.delete()) {
            			deleteCnt++;
            		}
            	}
        	}
        	log.info("######## FILE Delete : Exist File Count(" + list.size() + "), Deleted File Count(" + deleteCnt + ")");
        }
        return delCnt;
    }
}
