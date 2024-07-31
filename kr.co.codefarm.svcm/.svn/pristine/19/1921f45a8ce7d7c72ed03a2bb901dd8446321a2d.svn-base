package kr.co.codefarm.svcm.commons.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kr.co.codefarm.svcm.commons.utils.XmlUntypedObjectDeserializer;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

public class BaseController {
	
	private HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	private HttpClient client                              = null;
	
	private int _READ_TIMEOUT = 5000;
	private int _CONN_TIMEOUT = 3000;
	private int _MAX_CONN_TOT = 100;
	private int _MAX_CONN_ROT = 5;
	
	public BaseController() {
		this.factory.setReadTimeout(this._READ_TIMEOUT);
		this.factory.setConnectTimeout(this._CONN_TIMEOUT);
		
//		CredentialsProvider provider = new BasicCredentialsProvider();
//		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("", "");
//		provider.setCredentials(AuthScope.ANY, credentials);
		
//		this.client = HttpClientBuilder.create().
//				setMaxConnTotal(this._MAX_CONN_TOT).
//				setMaxConnPerRoute(this._MAX_CONN_ROT).
//				setDefaultCredentialsProvider(provider).
//				build();
	}
	
	public RestTemplate getRestTemplate() {		
		this.client = HttpClientBuilder.create().
				setMaxConnTotal(this._MAX_CONN_TOT).
				setMaxConnPerRoute(this._MAX_CONN_ROT).
				build();
		
		this.factory.setHttpClient(this.client);
		
		RestTemplate restTemplate = new RestTemplate(this.factory);
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		
		return restTemplate;
	}
	
	public void setReadTimeout(int readTimeout) {
		this.factory.setReadTimeout(readTimeout);
	}
	
	public void setConnectTimeout(int connectTimeout) {
		this.factory.setConnectTimeout(connectTimeout);
	}
	
	public void setMaxConnTotal(int maxConnTotal) {
		_MAX_CONN_TOT = maxConnTotal;
		client = HttpClientBuilder.create().setMaxConnTotal(_MAX_CONN_TOT).setMaxConnPerRoute(_MAX_CONN_ROT).build();
	}
	
	public void setMaxConnPerRoute(int maxConnPerRoute) {
		_MAX_CONN_ROT = maxConnPerRoute;
		client = HttpClientBuilder.create().setMaxConnTotal(_MAX_CONN_TOT).setMaxConnPerRoute(_MAX_CONN_ROT).build();
	}
	
	private Object convertBodyToObject(ResponseEntity<?> responseEntity) {
		Object bodyObj = null;
		
		if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			JsonParser jsonParser = new BasicJsonParser();
	        String jsonString     = responseEntity.getBody().toString();
	        
	        if (jsonString != null && !"".equals(jsonString) && jsonString.startsWith("[")) {
	        	bodyObj = jsonParser.parseList(jsonString);
	        } else if (jsonString != null && !"".equals(jsonString) && jsonString.startsWith("{")) {
	        	bodyObj = jsonParser.parseMap(jsonString);
	        } else {
	        	bodyObj = jsonString;
	        }
		}
		
		return bodyObj;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HttpEntity<?> getHttpEntity(Map<String, Object> parameter, String jsessionid) throws JsonProcessingException {
		HttpEntity<?> retHttpEntity = null;
		HttpHeaders headers         = new HttpHeaders();
		
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.setExpires(0);
		headers.setCacheControl("private, no-store, max-age=0");
		if (jsessionid != null && !"".equals(jsessionid)) {
			headers.add("Cookie", "JSESSIONID=" + jsessionid);
		}
		
		if (parameter != null && parameter.size() != 0) {
			retHttpEntity = new HttpEntity(new ObjectMapper().writeValueAsString(parameter), headers);
		} else {
			retHttpEntity = new HttpEntity(headers);
		}
		
		return retHttpEntity;
	}
	
	private Object exchange(String url, Map<String, Object> parameter, HttpMethod method, String jsessionid) {
		Object resultObj = null;
		
		try {
			resultObj = this.convertBodyToObject(this.getRestTemplate().exchange(url, method, this.getHttpEntity(parameter, jsessionid), String.class));
		} catch (RestClientException | JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return resultObj;
	}
	
	public Object get(String url, String type) {		
		return get(url, type, null);
	}
	
	public Object get(String url, String type, Map<String, Object> parameter) {
		return this.get(url, type, parameter, null);
	}
	
	@SuppressWarnings("unchecked")
	public Object get(String url, String type, Map<String, Object> parameter, String jsessionid) {
		String getParameter = "";
		if (parameter != null) {
			StringBuilder paramStr = new StringBuilder();
			for (String key : parameter.keySet()) {
				paramStr.append("&" + key + "=" + parameter.get(key));
			}
			getParameter = "?" + paramStr.substring(1);
		}
		Object returnObj = this.exchange(url + getParameter, null, HttpMethod.GET, jsessionid);
		
		if ("xml".equals(type)) {
			SimpleModule module = new SimpleModule().addDeserializer(Object.class, XmlUntypedObjectDeserializer.getInstance());
			XmlMapper xmlMapper = (XmlMapper) new XmlMapper().registerModule(module);
			
			Object object = null;
			try {
				object = xmlMapper.readValue(returnObj.toString(), Object.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			returnObj = (Map<String, Object>) object;
		}
		
		return returnObj;
	}
	
	public Object post(String url, Map<String, Object> parameter) {
		return this.post(url, parameter, null);
	}
	
	public Object post(String url, Map<String, Object> parameter, String jsessionid) {
		return this.exchange(url, parameter, HttpMethod.POST, jsessionid);
	}
	
	public Object put(String url, Map<String, Object> parameter) {
		return this.put(url, parameter, null);
	}
	
	public Object put(String url, Map<String, Object> parameter, String jsessionid) {
		return this.exchange(url, parameter, HttpMethod.PUT, jsessionid);
	}
	
	public Object patch(String url, Map<String, Object> parameter) {
		return this.patch(url, parameter, null);
	}
	
	public Object patch(String url, Map<String, Object> parameter, String jsessionid) {
		return this.exchange(url, parameter, HttpMethod.PATCH, jsessionid);
	}
	
	public Object delete(String url) {
		return this.delete(url, null);
	}
	
	public Object delete(String url, String jsessionid) {
		return this.exchange(url, null, HttpMethod.DELETE, jsessionid);
	}

}
