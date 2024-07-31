package kr.co.codefarm.svcm.web.sso.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import kr.co.codefarm.svcm.commons.login.handler.ScvmLoginSuccessHandler;
import kr.co.codefarm.svcm.commons.utils.LoginCookieUtil;

@Component
public class SSOLogoutSuccessHandler implements LogoutSuccessHandler {

	@Value("${use-sso}")
	private boolean ssoEnabled;

	@Value("${jwt.maindomain}")
	private String _DOMAIN;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if (authentication != null && authentication.getDetails() != null) {
			request.getSession().invalidate();
		}

		String redirectUrl = "/";

		// 로그인 쿠키 제거
		LoginCookieUtil.removeAccessCookie(response, _DOMAIN, ScvmLoginSuccessHandler.ACCESS_COOKIE_NAME);

		response.setCharacterEncoding("UTF-8");

		PrintWriter printWriter = response.getWriter();
		printWriter.write(redirectUrl);
		printWriter.flush();
	}

}
