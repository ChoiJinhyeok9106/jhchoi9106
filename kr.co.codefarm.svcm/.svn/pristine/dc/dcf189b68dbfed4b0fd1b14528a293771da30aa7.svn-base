package kr.co.codefarm.svcm.web.sso.filter;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.login.service.ScvmLoginService;
import kr.co.codefarm.svcm.commons.login.service.ScvmUserDetailsServiceImpl;
import kr.co.codefarm.svcm.commons.utils.JwtUtil;
import kr.co.codefarm.svcm.commons.utils.LoginCookieUtil;

@Component
public class SSOFilter implements Filter  {


	public final String ACCESS_COOKIE_NAME = "CDF-ACCESS-TOKEN";

	// 만료 시간이 5분 남았으면 만료 기간연장
	private final Duration expExtDuration = Duration.ofMinutes(55);
	private final Duration accessTokenExpDuration = Duration.ofMinutes(60);// 액세스 토큰 만료 기간

	@Value("${jwt.maindomain}")
	private String _DOMAIN;

	@Value("${service-origin-url}")
	private String _URL;

	@Value("${spring.profiles.active}")
	private String profile;

	private ScvmUserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private ScvmLoginService loginService;

	@Autowired
	public SSOFilter(ScvmUserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest _request   = (HttpServletRequest) request;
		HttpServletResponse _response = (HttpServletResponse) response;
		_response.setHeader("Strict-Transport-Security", "max-age=315360000; includeSubDomains; preload");
//
////		if(!profile.equals("local")) {
//			HttpSession _session          = _request.getSession();
//			SecurityContext securityContext = (SecurityContext)_session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
//		    Authentication authentication = null;
//		    boolean checkTime = true;
//		    if(securityContext != null){
//		    	authentication = securityContext.getAuthentication();
//		    }
//		    String ct = "";
//		    //과도한 필터체크로 10초에한번씩 체크하는것으로 수정
//		    if(authentication != null && authentication.getPrincipal() != null) {
//			    try {
//				    ct = WebUtils.getCookie(_request, ACCESS_COOKIE_NAME).getValue();
//			    	if((System.currentTimeMillis() - (1000*10)) < ((ScvmUserDetailsImpl) authentication.getPrincipal()).getLastCheckTime())
//						checkTime = false;
//			    	else
//			    		((ScvmUserDetailsImpl) authentication.getPrincipal()).setLastCheckTime(System.currentTimeMillis());
//			    }catch(NullPointerException e) {
//			    	e.printStackTrace();
//			    }
//		    }
//			if(authentication != null && ct.equals("")) {
//				_session.invalidate();
//				chain.doFilter(request, response);
//				return;
//			} else if(checkTime && ct.length() > 0) {
//				try {
//					Jws<Claims> jws = JwtUtil.verifyToken(ct);
//					// 토큰 검증
//					// 토큰 검증 됨
//					Claims claims = jws.getBody();// 사용자 정보 등
//					if(claims.getIssuedAt() == null) {
//						throw new RuntimeException("isa is null");
//					}
//					String userId = (String) claims.get("usr_id");
//					String loginVal = (String) claims.get("login_val");
//					if(userId == null || loginVal == null || userId.isBlank() || loginVal.isBlank()) {
//						throw new RuntimeException("usr_id or login_val is empty");
//					}
//					Map<String, Object> paramMap = new HashMap<>();
//					paramMap.put("USER_ID", userId);
//					List<Map<String, Object>> ret = loginService.loginDupCheck(paramMap);
//					if(ret.size() > 0) {
//						if(!ret.get(0).get("USE_DOMAIN").equals(ct)) {
//							throw new RuntimeException("Dup");
//						}
//					}else {
//						throw new RuntimeException("No Account");
//					}
//					// 토큰 재발급 필요하면 동일 정보에 만료 기간만 늘린 토큰 만들어 쿠키로 전달
//					// issued at 값은 건드리면 안된다.
//					String newAccessToken = null;
//					if(JwtUtil.timeCheck(claims.getExpiration().getTime(), expExtDuration.toMillis())) {
//						newAccessToken = JwtUtil.getToken(accessTokenExpDuration, userId, true);
//						paramMap.put("USER_ID", ret.get(0).get("USER_ID"));
//						paramMap.put("USE_DOMAIN", newAccessToken);
//						Cookie cookie = new Cookie(ACCESS_COOKIE_NAME, newAccessToken);
//						if(! StringUtils.isEmpty(_DOMAIN)) {
//							cookie.setDomain(_DOMAIN);
//						}
//						cookie.setPath("/");
//						cookie.setHttpOnly(true);
//						cookie.setMaxAge(-1);
//						cookie.setSecure(true);
//						_response.addCookie(cookie);
//						loginService.updateToken(paramMap);
//					}
//	
//					if(authentication == null) {
//						ScvmUserDetailsImpl user = (ScvmUserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(userId,true);
//						authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//	
//						if (securityContext == null) {
//							securityContext = new SecurityContextImpl();
//							securityContext.setAuthentication(authentication);
//							SecurityContextHolder.setContext(securityContext);
//						} else {
//							securityContext.setAuthentication(authentication);
//						}
//	
//						_request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
//					}else {
//						if(!userId.equals(((ScvmUserDetailsImpl) authentication.getPrincipal()).getInternalId())) {
//							throw new RuntimeException("로그인값이 다릅니다.");
//						}
//					}
//				}catch(ExpiredJwtException e){
//					e.printStackTrace();
//					//토큰만료시
//					if(authentication != null) {
//						_session.invalidate();
//					}
//					LoginCookieUtil.removeAccessCookie(_response, _DOMAIN, ACCESS_COOKIE_NAME);
//				}catch(RuntimeException e) {
//					e.printStackTrace();
//					if(authentication != null) {
//						_session.invalidate();
//					}
//					LoginCookieUtil.removeAccessCookie(_response, _DOMAIN, ACCESS_COOKIE_NAME);
//				}catch(Exception e) {
//					e.printStackTrace();
//				}finally{
//					chain.doFilter(_request, _response);
//				}
//				return;
//		    }
////		}
		chain.doFilter(_request, _response);
		return;
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
