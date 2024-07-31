/**
 * 
 */
package kr.co.codefarm.svcm.commons.controller.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.codefarm.svcm.commons.annotation.ParameterType;
import kr.co.codefarm.svcm.commons.annotation.ParameterUsage;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.service.file.FileService;

/**
 * @author PHS
 *
 */
@RestController
@RequestMapping(value = "/commons/file")
public class FileController {

	@Autowired
	private FileService fileService;

	/*get File*/
	@PostMapping({"/upload/excel"})
	public Map<String, Object> uploadExcel(@ParameterUsage(type = ParameterType.EXCEL) ParameterMap parameterMap) {
		return parameterMap.getMap();
	}
	
	/*get File*/
	@PostMapping("/upload")
    public Object uploadFile(HttpServletRequest request, ParameterMap parameterMap) {
		if(parameterMap.containsKey("upload_error")) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("ERROR", parameterMap.get("upload_error"));
			return returnMap;
		}else {
			String serviceAbbrNm = (String) parameterMap.get("serviceAbbrNm");
			String fileId = (String) parameterMap.get("fileId");
			String fileKey = (String) parameterMap.get("fileKey");
			String fileHangmok = (String) parameterMap.get("fileHangmok");
			List<Map<String, Object>> fileList = new ArrayList<>();
			Iterator<String> keys = parameterMap.keySet().iterator();
	    	
	        while( keys.hasNext() ){
	            String key = keys.next();
	            if(key.indexOf("file") > -1 && isStringDouble(key.replaceAll("file", ""))) {
	            	Map<String, Object> tempMap = new HashMap<>();
	            	Map<String, Object> fileMap = (Map<String, Object>) parameterMap.get(key);
	            	String fileName = (String) fileMap.get("filemap_name");
	            	String fileType = (String) fileMap.get("filemap_type");
	            	Double fileSize = (Double) fileMap.get("filemap_size");
	            	String filePath = (String) fileMap.get("filemap_path");
	            	
	            	tempMap.put("SERVICE_ABBR_NM", serviceAbbrNm);
	            	tempMap.put("FILE_ID", fileId);
	            	tempMap.put("FILE_KEY", fileKey);
	            	tempMap.put("FILE_HANGMOK", fileHangmok);
	            	tempMap.put("FILE_NM", fileName);
	            	tempMap.put("FILE_SIZE", fileSize);
	            	tempMap.put("FILE_PATH", filePath);
	            	tempMap.put("FILE_TYPE", fileType);
	            	
	            	tempMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
	            	tempMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
	            	tempMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));

	            	fileService.insertFile(tempMap);
	            	if(fileId.equals("SMN_USER_V") && fileHangmok.equals("USER_PIC")) {
	            		((ScvmUserDetailsImpl)((SecurityContext)request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY)).getAuthentication().getPrincipal()).setUserPic(filePath);
	            	}
	            }
	        }
	        fileList = fileService.getFileList(serviceAbbrNm, fileId, fileKey, fileHangmok, null);
	        return fileList;
		}
    }
	
	/*get File*/
	@PostMapping("/upload-editor")
    public Object uploadFileEditor(ParameterMap parameterMap) {
		if(parameterMap.containsKey("upload_error")) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			return returnMap.put("ERROR", parameterMap.get("upload_error"));
		}else {
			String serviceAbbrNm = (String) parameterMap.get("serviceAbbrNm");
			String fileId = (String) parameterMap.get("fileId");
			String fileKey = (String) parameterMap.get("fileKey");
			String fileHangmok = (String) parameterMap.get("fileHangmok");
			String returnUrl = "";
			List<Map<String, Object>> fileList = new ArrayList<>();
			Iterator<String> keys = parameterMap.keySet().iterator();
	    	
	        while( keys.hasNext() ){
	            String key = keys.next();
	            if(key.indexOf("file") > -1 && isStringDouble(key.replaceAll("file", ""))) {
	            	Map<String, Object> tempMap = new HashMap<>();
	            	Map<String, Object> fileMap = (Map<String, Object>) parameterMap.get(key);
	            	String fileName = (String) fileMap.get("filemap_name");
	            	String fileType = (String) fileMap.get("filemap_type");
	            	Double fileSize = (Double) fileMap.get("filemap_size");
	            	String filePath = (String) fileMap.get("filemap_path");
	            	
	            	returnUrl = filePath.replace("\\", "/");
	            	
	            	tempMap.put("SERVICE_ABBR_NM", serviceAbbrNm);
	            	tempMap.put("FILE_ID", fileId);
	            	tempMap.put("FILE_KEY", fileKey);
	            	tempMap.put("FILE_HANGMOK", fileHangmok);
	            	tempMap.put("FILE_NM", fileName);
	            	tempMap.put("FILE_SIZE", fileSize);
	            	tempMap.put("FILE_PATH", filePath);
	            	tempMap.put("FILE_TYPE", fileType);
	            	
	            	tempMap.put("SYSTEM_ID", parameterMap.get("SYSTEM_ID"));
	            	tempMap.put("SYSTEM_IP", parameterMap.get("SYSTEM_IP"));
	            	tempMap.put("SYSTEM_PGM", parameterMap.get("SYSTEM_PGM"));

	            	fileService.insertFile(tempMap);
	            }
	        }
	        return returnUrl;
		}
    }
	
	/*get File*/
	@PostMapping("/file-upload")
    public Object fileUpload(ParameterMap parameterMap) {
		if(parameterMap.containsKey("upload_error")) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			return returnMap.put("ERROR", parameterMap.get("upload_error"));
		}else {
			List<Map<String, Object>> fileList = new ArrayList<>();
			Iterator<String> keys = parameterMap.keySet().iterator();
	    	
	        while( keys.hasNext() ){
	            String key = keys.next();
	            if(key.indexOf("file") > -1 && isStringDouble(key.replaceAll("file", ""))) {
	            	Map<String, Object> tempMap = new HashMap<>();
	            	Map<String, Object> fileMap = (Map<String, Object>) parameterMap.get(key);
	            	String fileName = (String) fileMap.get("filemap_name");
	            	String fileType = (String) fileMap.get("filemap_type");
	            	Double fileSize = (Double) fileMap.get("filemap_size");
	            	String filePath = (String) fileMap.get("filemap_path");
	            	
	            	tempMap.put("FILE_NM", fileName);
	            	tempMap.put("FILE_SIZE", fileSize);
	            	tempMap.put("FILE_PATH", filePath);
	            	tempMap.put("FILE_TYPE", fileType);
	            	fileList.add(tempMap);
	            }
	        }
	        return fileList;
		}
    }

	/*get File*/
	@GetMapping("")
    public List<Map<String, Object>> getFile(ParameterMap parameterMap) {
		String serviceAbbrNm = (String) parameterMap.get("serviceAbbrNm");
		String fileId = (String) parameterMap.get("fileId");
		String fileKey = (String) parameterMap.get("fileKey");
		String fileHangmok = (String) parameterMap.get("fileHangmok");
        String fileNo = (String) parameterMap.get("fileNo");
        
        List<Map<String, Object>> list = fileService.getFileList(serviceAbbrNm, fileId, fileKey, fileHangmok, fileNo);
        return list;
    }
    
    /*insert file*/
    @PostMapping("")
    public Object insertFile(ParameterMap parameterMap) {
        int insCnt = fileService.insertFile(parameterMap.getMap());
        return insCnt;
    }
    
    /*delete file*/
    @DeleteMapping("")
    public Object deleteFile(HttpServletRequest request, ParameterMap parameterMap) {
		String serviceAbbrNm = (String) parameterMap.get("serviceAbbrNm");
		String fileId = (String) parameterMap.get("fileId");
		String fileKey = (String) parameterMap.get("fileKey");
		String fileHangmok = (String) parameterMap.get("fileHangmok");
        String fileNo = (String) parameterMap.get("fileNo");
        
        int delCnt = fileService.deleteFile(serviceAbbrNm, fileId, fileKey, fileHangmok, fileNo);
    	if(fileId.equals("SMN_USER_V") && fileHangmok.equals("USER_PIC")) {
    		((ScvmUserDetailsImpl)((SecurityContext)request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY)).getAuthentication().getPrincipal()).setUserPic("");
    	}
        return delCnt;
    }
    
    public static boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
