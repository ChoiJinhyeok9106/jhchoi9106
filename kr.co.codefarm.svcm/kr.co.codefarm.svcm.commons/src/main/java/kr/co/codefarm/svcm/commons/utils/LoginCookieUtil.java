package kr.co.codefarm.svcm.commons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

/**
 * HttpSender
 *
 * Modification Information
 * 2023/10/25 pyk 최초작성
 */
public class LoginCookieUtil {

	public static void removeAccessCookie(HttpServletResponse response, String domain, String cookieName) {
		// 도메인 없는 것도 삭제
		Cookie cookie2 = new Cookie(cookieName, "");
		cookie2.setPath("/");
		cookie2.setHttpOnly(true);
		cookie2.setSecure(true);
		cookie2.setMaxAge(0);// 쿠키 없애기
		response.addCookie(cookie2);

		if(! StringUtils.isEmpty(domain)) {
			Cookie cookie1 = new Cookie(cookieName, "");
			cookie1.setDomain(domain);
			cookie1.setPath("/");
			cookie1.setHttpOnly(true);
			cookie1.setSecure(true);
			cookie1.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
			response.addCookie(cookie1);
		}
	}

	public static void addAccessCookie(HttpServletResponse response, String domain, String cookieName, String cookieValue) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		if(! StringUtils.isEmpty(domain)) {
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
	}

}