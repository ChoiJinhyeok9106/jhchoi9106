package kr.co.codefarm.svcm.commons.login.handler;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.login.service.ScvmLoginService;
import kr.co.codefarm.svcm.commons.utils.EtcUtil;
import kr.co.codefarm.svcm.commons.utils.JwtUtil;
import kr.co.codefarm.svcm.commons.utils.LoginCookieUtil;
import kr.co.codefarm.svcm.commons.utils.cipher.AES128Util;
import kr.co.codefarm.svcm.commons.utils.cipher.RSAUtil;

@Component
public class ScvmLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private ScvmLoginService scvmLoginService;
	
	@Value("${service-origin-url}")
	private String _URL;
	
	@Value("${jwt.maindomain}")
	private String _DOMAIN;
	
	@Value("${keyfile}")
	private String _KEY_FILEPATH;
	
	public static final String IS_MOBILE = "MOBILE";
	private static final String IS_PHONE = "PHONE";
	public static final String IS_TABLET = "TABLET";
	public static final String IS_PC = "PC";
	

	public static final String ACCESS_COOKIE_NAME = "CDF-ACCESS-TOKEN";
	private static final Duration accessTokenExpDuration = Duration.ofMinutes(60);// 액세스 토큰 만료 기간
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		
		Map<String, Object> param = new HashMap<>();
		PrivateKey privateKey     = (PrivateKey) request.getSession().getAttribute("_RSA_PRIVATE_KEY_");
		try {
			String id                 = RSAUtil.decryptionUsingModulusAndExponent(privateKey, (String) request.getParameter("uid"));
			String returnUrl		  = (String) request.getParameter("returnUrl");
			ScvmUserDetailsImpl userDetails = (ScvmUserDetailsImpl) authentication.getPrincipal();
			
	
			param.put("USER_ID",   id);
			param.put("SYSTEM_IP", EtcUtil.getIP(request));
			param.put("ACCESS_YN", "Y");
	
			String accessToken = JwtUtil.getToken(accessTokenExpDuration, userDetails.getInternalId(), true);
			param.put("USE_DOMAIN", accessToken);
			scvmLoginService.setAccount(param);
	
			param.put("TEXT",
					"["
					+ "{User-Agent:"+request.getHeader("User-Agent")+"},"
					+ "{IP:"+getIp(request)+"},"
					+ "{"
					   + "DEPT_NM:"+userDetails.getDeptNm()+","
			   		+ "}"
			   	  + "]");
			
			param.put("ALARM_SEQ", 0);
			param.put("TITLE",     userDetails.getUserNm() + " 로그인");
			
			scvmLoginService.insertLog(param);
			
			LoginCookieUtil.addAccessCookie(response, _DOMAIN, ACCESS_COOKIE_NAME, accessToken);
			LocalDate dt = LocalDate.parse(userDetails.getLatestPwdChgDt(),DateTimeFormatter.BASIC_ISO_DATE);
			
			if(dt.plusDays(90).isBefore(LocalDate.now())) {
				String redirectPage = "/comm/redirectmessage.html?";
				String redirectUrl = "/html/main/index.html?app=mypage\\menu=03000000";
				String msg = "비밀번호를 변경한지 90일이 지났습니다. 비밀번호 재설정을 해주세요."; 
				returnUrl = redirectPage + URLEncoder.encode("returnUrl=" + redirectUrl + "&errorMsg=" + msg, "UTF-8");
			}
			if(returnUrl != null && !returnUrl.equals("")) {
				if(returnUrl.startsWith(_URL))
					response.sendRedirect(returnUrl.replace("\r","").replace("\n", ""));
				else 
					response.sendRedirect(_URL + "/html/main/index.html");
			}else {
				response.sendRedirect(_URL + "/html/main/index.html");
			}
		} catch(NullPointerException e) {
			e.printStackTrace();
			request.getSession().invalidate();
			response.sendRedirect(_URL);
		}
		
	}
	
	/**
	 * User-Agent Return
	 * @param req
	 * @return
	 */
	public String getAgent(HttpServletRequest req) {
	    String userAgent = req.getHeader("User-Agent").toUpperCase();
	    return userAgent;
	}
	
	/**
	 * User-Ip Return
	 * @param req
	 * @return
	 */
	public String getIp(HttpServletRequest req) {
	    String ip = req.getHeader("X-FORWARDED-FOR"); 
	    
	    if (ip == null || ip.length() == 0) { 
	    	ip = req.getHeader("Proxy-Client-IP"); 
	    }
	    if (ip == null || ip.length() == 0) { 
	    	ip = req.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0) { 
	    	ip = req.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null || ip.length() == 0) { 
	    	ip = req.getRemoteAddr(); 
	    }
	    
	    return ip;
	}
}
