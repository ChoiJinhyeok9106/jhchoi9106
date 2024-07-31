package kr.co.codefarm.svcm.web.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint { 

	public CustomAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		String ajaxHeader = request.getHeader("accept");
		
        if (ajaxHeader.indexOf("application/json") != -1) {
        	PrintWriter out;
			try {
				out = response.getWriter();
				response.setStatus(403);
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        String jsonStr = "{\"error\":\"Access Denied\", \"message\":\"로그인토큰이 만료되었거나 중복로그인으로 인해 로그아웃 됩니다.\", \"timestamp\":\"" + System.currentTimeMillis() + "\", \"status\":\"403\"}";
		        out.print(jsonStr);
		        out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
            super.commence(request, response, authException);
        }
	}

}
