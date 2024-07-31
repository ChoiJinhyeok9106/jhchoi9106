/**
 * 
 */
package kr.co.codefarm.svcm.commons.resolvers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tika.Tika;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.codefarm.svcm.commons.annotation.ParameterType;
import kr.co.codefarm.svcm.commons.annotation.ParameterUsage;
import kr.co.codefarm.svcm.commons.component.ExcelReaderComponent;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.ParameterMap;
import kr.co.codefarm.svcm.commons.properties.SystemProp;
import kr.co.codefarm.svcm.commons.utils.EtcUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

/**
 * @author Dki_s
 *
 */

@Slf4j
public class ParameterMapArgumentResolver implements HandlerMethodArgumentResolver {
	
	private ExcelReaderComponent excelReaderComponent;
	private SystemProp prop;

    private String         _SESSION_KEY_ID;
    private String         _SUFFIX_LIST_PARAM;
    private String         _UPLOAD_DIR;
    private String         _UPLOAD_PATH;
    private String         _UPLOAD_FIXED_NAME;
    private String         _UPLOAD_MAX_SIZE;
    private String         _UPLOAD_ERR;
    private String         _UPLOAD_CNT;
    private List<String>   _UPLOAD_ALLOW_MIMETYPES;
    private String         _FILEMAP_PATH;
    private String         _FILEMAP_NAME;
    private String         _FILEMAP_TYPE;
    private String         _FILEMAP_SIZE;
    private String         _SUFFIX_JSON_LIST_PARAM;
    
    private String         _HOMEDIR;
    private Tika tika = new Tika();
    
    public ParameterMapArgumentResolver(SystemProp prop, ExcelReaderComponent excelReaderComponent, String homedir) {
    	this.excelReaderComponent = excelReaderComponent;
    	this.prop                 = prop;
    	
    	this._SESSION_KEY_ID         = this.prop.getSessionKeyId();
    	this._SUFFIX_LIST_PARAM      = this.prop.getSuffixListParam();
    	this._UPLOAD_DIR             = this.prop.getUploadDir();
    	this._UPLOAD_PATH            = this.prop.getUploadPath();
    	this._UPLOAD_FIXED_NAME      = this.prop.getUploadFixedName();
    	this._UPLOAD_MAX_SIZE        = this.prop.getUploadMaxSize();
    	this._UPLOAD_ERR             = this.prop.getUploadErr();
    	this._UPLOAD_CNT             = this.prop.getUploadCnt();
    	this._UPLOAD_ALLOW_MIMETYPES = this.prop.getUploadAllowMimetypes();
    	this._FILEMAP_PATH           = this.prop.getFilemapPath();
    	this._FILEMAP_NAME           = this.prop.getFilemapName();
    	this._FILEMAP_TYPE           = this.prop.getFilemapType();
    	this._FILEMAP_SIZE           = this.prop.getFilemapSize();
    	this._SUFFIX_JSON_LIST_PARAM = this.prop.getSuffixJsonListParam();
    	
    	this._HOMEDIR                = homedir;
    	if ("".equals(this._HOMEDIR)) {
    		this._HOMEDIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "public";
    	}
    }
    
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return ParameterMap.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		log.debug("#######################################################");
		log.debug("################## CDF Parameter Log ##################");
		log.debug("#######################################################");
		
		ParameterUsage parameterUsageAnnotation = parameter.getParameterAnnotation(ParameterUsage.class);
		
		ParameterMap parameterMap  = new ParameterMap();
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

		if (parameterUsageAnnotation == null) {
			parameterUsageAnnotation = new ParameterUsage() {
				@Override
				public Class<? extends Annotation> annotationType() {
					return null;
				}
				
				@Override
				public ParameterType type() {
					return ParameterType.NORMAL;
				}
			};
		}
		
		switch (parameterUsageAnnotation.type()) {
		case EXCEL:
			log.debug("---------->       Excel  Type Process       <----------");
			this.excelProcessing(parameterMap, request);
			break;
		case NORMAL:
		default:
			log.debug("---------->       Normal Type Process       <----------");
			this.normalProcessing(webRequest, parameterMap, request);
			break;
		}
		
		return parameterMap;
	}
	
	private Object normalProcessing(NativeWebRequest webRequest, ParameterMap parameterMap, HttpServletRequest request) throws IOException, MagicParseException, MagicMatchNotFoundException, MagicException {
//		this.getRequestBody(webRequest);
		this.payloadSetting(parameterMap, request);
        log.info(" ---> Payload processing complete...");
		this.parameterMapSetting(parameterMap, request);
		log.info(" ---> ParameterMap processing complete...");
		this.defaultValueSetting(parameterMap, request);
		log.info(" ---> Default parameter value processing complete...");
        
        if (ServletFileUpload.isMultipartContent(request)) {
    		this.normalFileSetting(parameterMap, request);
    		log.info(" ---> Normal multipart processing complete...");
        }
        
        log.info(" ---> " + parameterMap.toJSONString());
        
        return parameterMap;
	}
	
	private Object excelProcessing(ParameterMap parameterMap, HttpServletRequest request) throws IOException, IllegalStateException, MagicParseException, MagicMatchNotFoundException, MagicException {
		this.parameterMapSetting(parameterMap, request);
		log.info(" ---> ParameterMap processing complete...");
		
		if (ServletFileUpload.isMultipartContent(request)) {
    		this.excelFileSetting(parameterMap, request);
    		log.info(" ---> Excel multipart processing complete...");
        }
        
        return parameterMap;
	}
	
	private void payloadSetting(ParameterMap parameterMap, HttpServletRequest request) throws IOException {
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
		        	List<Object> payload = jsonParser.parseList(jsonString);
		        	parameterMap.put(this._SUFFIX_JSON_LIST_PARAM, payload);
		        } else if (jsonString.startsWith("{")) {
		        	Map<String, Object> payload = jsonParser.parseMap(jsonString);
		        	parameterMap.putAll(payload);
		        }
		    } catch(IOException e) {
				log.error("Non-JSON String");
			} finally {
		        reader.close();
		    }
		}
	}
	
	private void parameterMapSetting(ParameterMap parameterMap, HttpServletRequest request) throws IOException {
		Map<String, String[]> requestMap = request.getParameterMap();
		
        for (String key : requestMap.keySet()) {
            String[] values        = requestMap.get(key);
            String parameterValue  = "";
            List<String> valueList = new ArrayList<>();

            if (values.length == 1) {
                parameterValue = values[0];
            } else {
                for (int idx = 0; idx < values.length; idx++) {
                    String listValue = values[idx];
                    parameterValue += "," + listValue;
                    valueList.add(listValue);
                }
                parameterValue = parameterValue.substring(1);
            }

            String value = parameterValue;
            
            if (values.length != 1) {
                parameterMap.put(key + this._SUFFIX_LIST_PARAM, valueList);
            }
            parameterMap.put(key, value);
        }
        
        log.debug("---> Parameter Map : " + parameterMap.toString());
	}
	
	private void defaultValueSetting(ParameterMap parameterMap, HttpServletRequest request) {
		String ip = EtcUtil.getIP(request);
		String pg = request.getServletPath();
		String id = "";
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        if (principal instanceof ScvmUserDetailsImpl) {
//	        	id = ((ScvmUserDetailsImpl) principal).getUserId();
	        	id = ((ScvmUserDetailsImpl) principal).getInternalId();
	        }
	    }
		
//		if (request.getSession().getAttribute(this._SESSION_KEY_ID) != null) {
//			id = request.getSession().getAttribute(this._SESSION_KEY_ID).toString();
//		}
		
        parameterMap.put("SYSTEM_ID",  id);
        parameterMap.put("SYSTEM_IP",  ip);
        parameterMap.put("SYSTEM_PGM", pg);
	}
	
	private void excelFileSetting(ParameterMap parameterMap, HttpServletRequest request) throws IllegalStateException, IOException, MagicParseException, MagicMatchNotFoundException, MagicException {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator                               = multipartHttpServletRequest.getFileNames();
        MultipartFile multipartFile                             = null;
        String mimeType                                         = "";
        String fileUploadErrMsg                                 = "";
        boolean fileCheck                                       = true;
        List<String> saveFileList                               = new ArrayList<>();
        
        List<String> headerData                                 = null;
        boolean isHeaderInclude                                 = false;
        
        if (parameterMap.containsKey("header_data") && !"".equals(parameterMap.get("header_data"))) {
        	headerData = new ArrayList<String>(Arrays.asList(parameterMap.get("header_data").toString().split(",")));
        }
        if (parameterMap.containsKey("header_include") && !"".equals(parameterMap.get("header_include"))) {
        	isHeaderInclude = parameterMap.get("header_include").toString().equals("true");
        }
        
        String[] officeMimeTypeList = {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};
        
        while (iterator.hasNext()) {
            String fileName = iterator.next();
            multipartFile = multipartHttpServletRequest.getFile(fileName);
            if (!multipartFile.isEmpty()) {
                boolean typeCheck = false;
                
                try {
                	mimeType = Magic.getMagicMatch(multipartFile.getBytes(), false).getMimeType();
                    for (String type : officeMimeTypeList) {
                        if (mimeType.startsWith(type)) {
                            typeCheck = true;
                            break;
                        }
                    }
                    if (!typeCheck) {
                        fileCheck = false;
                        fileUploadErrMsg = "업로드가 불가능한 파일입니다.";
                    }
                } catch (IllegalStateException e) {
                	e.printStackTrace();
                }

                if (!fileCheck) {
                    break;
                }

                parameterMap.put(fileName, this.excelReaderComponent.excelToList(multipartFile, headerData, isHeaderInclude));
                saveFileList.add(fileName);
            }
        }

        if (!fileCheck) {
        	for (String key : saveFileList) {
                parameterMap.remove(key);
            }
            parameterMap.put(this._UPLOAD_ERR, fileUploadErrMsg);
        }
		parameterMap.put(this._UPLOAD_CNT, saveFileList.size());
	}
	
	private void normalFileSetting(ParameterMap parameterMap, HttpServletRequest request) throws IllegalStateException, IOException, MagicParseException, MagicMatchNotFoundException, MagicException {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator                               = multipartHttpServletRequest.getFileNames();
        MultipartFile multipartFile                             = null;
        String mimeType                                         = "";
        boolean fileCheck                                       = true;
        Map<String, File> saveFileMap                           = new HashMap<>();
        String fileUploadErrMsg                                 = "";
        
        while (iterator.hasNext()) {
            String fileName = iterator.next();
            multipartFile = multipartHttpServletRequest.getFile(fileName);
            if (!multipartFile.isEmpty()) {
                String rootPath = this._HOMEDIR + File.separator + this._UPLOAD_DIR;
                
                String subPath  = "";
                
                if (parameterMap.get(this._UPLOAD_PATH) != null) {
                    subPath  = (String)parameterMap.get(this._UPLOAD_PATH);

                    if (!subPath.startsWith("/") && !subPath.startsWith("\\")) {
                        subPath = File.separator + subPath;
                    }

                    if (!subPath.endsWith("/") && !subPath.endsWith("\\")) {
                        subPath = subPath + File.separator;
                    }

                    subPath = subPath.replace("/", File.separator).replace("\\", File.separator);
                }

                long maxSize = 0;
                
                if (parameterMap.get(this._UPLOAD_MAX_SIZE) != null && !parameterMap.get(this._UPLOAD_MAX_SIZE).equals("")) {
                    maxSize  = Integer.valueOf((String)parameterMap.get(this._UPLOAD_MAX_SIZE)) * 1024000;
                }
                if (maxSize != 0 && multipartFile.getSize() > maxSize) {
                    fileCheck = false;
                    fileUploadErrMsg = "파일용량이 너무큽니다.";
                }

                boolean typeCheck = false;
            	boolean checkExt = false;
                
                try {
                	String[] extTypes = { "doc" 
                						, "docx"
	                					, "gif"
	                					, "jpeg"
	                					, "jpg"
	                					, "mp3"
	                					, "mp4"
	                					, "mpeg"
	                					, "png"
	                					, "pdf"
	                					, "ppt"
	                					, "pptx"
	                					, "svg"
	                					, "txt"
	                					, "wav"
	                					, "xls"
	                					, "xlsx"
	                					, "zip"
	                					, "hwp"
	                					, "hwpx"
	                					, "xml"
	                					, "BID"
                						};
                	String checkFileName = multipartFile.getOriginalFilename();
                	String fileExt = "";
                	if(checkFileName.lastIndexOf(".") > 0) {
                		fileExt = checkFileName.substring(checkFileName.lastIndexOf(".")+1);
                	}
                	for(String extType : extTypes) {
                		System.out.println(extType + "" + fileExt );
                		if(extType.equals(fileExt)){
                			checkExt = true;
                			break;
                		}
                	}
                	mimeType = tika.detect(multipartFile.getBytes());
                	String fileId = parameterMap.get("fileId")!=null ? parameterMap.get("fileId").toString() : "";
                	String fileHangmok = parameterMap.get("fileHangmok")!=null ? parameterMap.get("fileHangmok").toString() : "";
                	if(fileId.equals("SMN_DEFAULTINFO") && fileHangmok.startsWith("logoImg") && mimeType.equals("image/svg+xml")) {
                		typeCheck = true;
                	} else if(!mimeType.equals("image/svg+xml")) {
	                	for (String type : this._UPLOAD_ALLOW_MIMETYPES) {
	                        if (mimeType.startsWith(type)) {
	                            typeCheck = true;
	                            break;
	                        }
	                    }
                	}
                	System.out.println(typeCheck);
                	System.out.println(checkExt);
                    if (!typeCheck || !checkExt) {
                        fileCheck = false;
                        fileUploadErrMsg = "업로드가 불가능한 파일입니다.";
                    }
                } catch (IllegalStateException e) {
                	e.printStackTrace();
                }

                String uniqeFileName = UUID.randomUUID().toString() + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                if (parameterMap.get(this._UPLOAD_FIXED_NAME) != null && !"".equals(parameterMap.get(this._UPLOAD_FIXED_NAME))) {
                	uniqeFileName = parameterMap.get(this._UPLOAD_FIXED_NAME).toString();
                }
                File saveFile        = new File(rootPath + subPath + uniqeFileName);
                
                if (saveFile.getParentFile() != null) {
                    if (!saveFile.getParentFile().isDirectory()) {
                        saveFile.getParentFile().mkdirs();
                    }
                }

                Map<String, Object> fileMap = new HashMap<String, Object>();
                fileMap.put(this._FILEMAP_PATH, subPath + uniqeFileName);
                fileMap.put(this._FILEMAP_NAME, multipartFile.getOriginalFilename());
                fileMap.put(this._FILEMAP_TYPE, mimeType);
                fileMap.put(this._FILEMAP_SIZE, Math.round((multipartFile.getSize() / 1024) * 100) / 100.0);

                log.debug(" " + fileName + "(SAVE) : " + rootPath + fileMap.get(this._FILEMAP_PATH));
                log.debug(" " + fileName + "(PATH) : " + fileMap.get(this._FILEMAP_PATH));
                log.debug(" " + fileName + "(NAME) : " + fileMap.get(this._FILEMAP_NAME));
                log.debug(" " + fileName + "(TYPE) : " + fileMap.get(this._FILEMAP_TYPE));
                log.debug(" " + fileName + "(SIZE) : " + fileMap.get(this._FILEMAP_SIZE));

                parameterMap.put(fileName, fileMap);
                saveFileMap.put(fileName, saveFile);
                //multipartFile.transferTo(saveFile);
                
                InputStream input = multipartFile.getInputStream();
            	Files.copy(input, saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            	input.close();
                if (!fileCheck) {
                    break;
                }
            }
        }

        if (!fileCheck) {
            for (String key : saveFileMap.keySet()) {
                parameterMap.remove(key);
                saveFileMap.get(key).delete();
            }
            parameterMap.put(this._UPLOAD_ERR, fileUploadErrMsg);
        }
		parameterMap.put(this._UPLOAD_CNT, saveFileMap.size());
	}
	
//	private String getRequestBody(NativeWebRequest nativeWebRequest) {
//		HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
//		String retVal              = "";
//		
//		try {
//			retVal = IOUtils.toString(request.getInputStream());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("#####################-------------#####################");
//		System.out.println(retVal);
//		System.out.println("#####################-------------#####################");
//		
//		return retVal;
//	}

}
