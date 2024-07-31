package kr.co.codefarm.svcm.commons.login.handler;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import kr.co.codefarm.svcm.commons.login.service.ScvmLoginService;
import kr.co.codefarm.svcm.commons.utils.EtcUtil;
import kr.co.codefarm.svcm.commons.utils.cipher.RSAUtil;

@Component
public class ScvmLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Value("${service-origin-url}")
	private String _URL;
	
	@Autowired
	private ScvmLoginService scvmLoginService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		String redirectPage = "/comm/redirectmessage.html?";
//		String returnUrl    = request.getHeader("Referer");
		String returnUrl    = "/";
		String errorMsg     = exception.getMessage();

		if ("LF".equals(exception.getMessage())) {
			errorMsg = "로그인 5회 실패로 로그인이 제한되었습니다.\n[비밀번호 분실]에서 비밀번호를 재생성하시기 바랍니다.";
		}
		if ("TO".equals(exception.getMessage())) {
			errorMsg = "인증시간이 초과하였습니다. 다시시도해주세요.";
		}
		if (exception.getMessage().startsWith("KE")) {
			try {
				Map<String, Object> param = new HashMap<>();
				PrivateKey privateKey     = (PrivateKey) request.getSession().getAttribute("_RSA_PRIVATE_KEY_");
				String id                 = RSAUtil.decryptionUsingModulusAndExponent(privateKey, (String) request.getParameter("uid"));
				
				param.put("USER_ID",   id);
				param.put("SYSTEM_IP", EtcUtil.getIP(request));
				param.put("ACCESS_YN", "N");
				
				scvmLoginService.setAccount(param);
				errorMsg = "올바르지 않은 인증번호입니다.";
			} catch(NullPointerException e) {
				e.printStackTrace();
			}
		}
		if ("TE".equals(exception.getMessage())) {
			errorMsg = "비정상적인 접근입니다.";
		}
		if ("NP".equals(exception.getMessage())) {
			errorMsg = "암호화 모듈 문제입니다. 관리자에게 문의하세요.";
		}
		if ("NU".equals(exception.getMessage())) {
			errorMsg = "사용할 수 없는 사용자입니다. 관리자에게 문의하세요.";
		}
		if (exception.getMessage().startsWith("WP") || "NE".equals(exception.getMessage())) {
			errorMsg = "미인증된 사용자 이거나 일치하는 사용자가 없습니다.";
			if (exception.getMessage().startsWith("WP")) {
				if(request.getParameter("uid") != null) {
					Map<String, Object> param = new HashMap<>();
					PrivateKey privateKey     = (PrivateKey) request.getSession().getAttribute("_RSA_PRIVATE_KEY_");
					String id                 = RSAUtil.decryptionUsingModulusAndExponent(privateKey, (String) request.getParameter("uid"));
					
					param.put("USER_ID",   id);
					param.put("SYSTEM_IP", EtcUtil.getIP(request));
					param.put("ACCESS_YN", "N");
					scvmLoginService.setAccount(param);
					errorMsg = "잘못된 로그인ID 또는 비밀번호 입니다.";
				}
			}
		}
		
		if (returnUrl == null) {
			returnUrl = "/";
		}

		String redirectUrl = redirectPage + URLEncoder.encode("returnUrl=" + returnUrl + "&errorMsg=" + errorMsg, "UTF-8");
		
		response.sendRedirect(_URL + redirectUrl);
	}

}
