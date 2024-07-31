package kr.co.codefarm.svcm.commons.login.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class ScvmAccessDeniedHandler implements AccessDeniedHandler {

	@Value("${service-origin-url}")
	private String _URL;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		String redirectPage = "/comm/redirectmessage.html?";
//		String returnUrl = request.getHeader("Referer");
		String returnUrl = "/html/main/index.html";
		String errorMsg  = accessDeniedException.getMessage();
		
		if (returnUrl == null) {
			returnUrl = "/html/main/index.html";
		}
		
		String redirectUrl = redirectPage + URLEncoder.encode("returnUrl=" + returnUrl + "&errorMsg=" + errorMsg, "UTF-8");
		
		response.sendRedirect(_URL + redirectUrl);
	}

}
