package kr.co.codefarm.svcm.commons.interceptor;

import kr.co.codefarm.svcm.commons.api.dto.AuthDto;
import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.map.AuthMap;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Value("${service-origin-url}")
	private String _URL;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {		
		if (request.getRequestURI().startsWith("/html/main/index.html")) {
			ScvmUserDetailsImpl user = (ScvmUserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("usercheck : " + user.getAuthorities());
			log.info("URI [" + request.getRequestURI() + "] Authentication Check");
			
			// 약관 동의는 통합사용자 로그인에서 처리 함으로 false로 지정, 2020.09.23 박해수	
			// KREN 필수약관 동의 회원가입떄 받기떄문에 로그인 때 체크하는부분 주석처리 2022.07.04 윤수영
//			if (user.getAgreeYn().equals("N")) {
//				log.info("###### Agree Denied ######");
//				String redirectUrl;
//				try {
//					redirectUrl = "/comm/redirectmessage.html?" + URLEncoder.encode("returnUrl=/html/main/signup_terms.html?signup=pass&errorMsg=필수 약관 동의가 필요합니다.", "UTF-8");
//					response.setStatus(403);
//					response.sendRedirect(_URL + redirectUrl);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return false;
//			}
			
			String app  = request.getParameter("app");
			String menu = request.getParameter("menu");
			if (app == null && menu == null) {
				return true;
			}
			
            for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
				log.info("###### ROLE - " + grantedAuthority.getAuthority().substring(5));
				List<AuthDto> authDtoList = AuthMap.get(grantedAuthority.getAuthority().substring(5));
				
				if (authDtoList != null && authDtoList.size() > 0) {
					for (AuthDto authDto : authDtoList) {
						if (app.equals(authDto.getAPP_ID()) && menu.equals(authDto.getMENU_CD())) {
							return true;
						}
					}
				}
			}
			
			log.info("###### Access Denied ######");
			String redirectUrl;
			try {
				redirectUrl = "/comm/redirectmessage.html?" + URLEncoder.encode("errorMsg=프로그램에 대한 접근 권한이 없습니다.", "UTF-8");
				response.setStatus(403);
				response.sendRedirect(_URL + redirectUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		if (!"/api/system-manager/app/subscribe-apps".equals(request.getRequestURI()) && (request.getRequestURI().startsWith("/api/system-manager/app/") || request.getRequestURI().startsWith("/service/systemmanager/html/sub/")) ) {
			ScvmUserDetailsImpl user = null;
			
			if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
                user = (ScvmUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                log.info("usercheck : " + user.getAuthorities());
			}
			
			log.info("URI [" + request.getRequestURI() + "] Authentication Check");
			
//			if (user != null && user.getAgreeYn().equals("N")) {
//				log.info("###### Agree Denied ######");
//				PrintWriter out;
//				try {
//					out = response.getWriter();
//					response.setStatus(403);
//			        response.setContentType("application/json");
//			        response.setCharacterEncoding("UTF-8");
//			        String jsonStr = "{\"error\":\"Agree Denied\", \"message\":\"필수 약관 동의가 필요합니다.\", \"timestamp\":\"" + System.currentTimeMillis() + "\", \"status\":\"403\"}";
//			        out.print(jsonStr);
//			        out.flush();
//				} catch (IOException e) {
//					e.printStackTrace();
//					return false;
//				}
//				return false;
//			}
			
			log.info("#######################################################################");
			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String itemName = headerNames.nextElement();
				Enumeration<String> items = request.getHeaders(itemName);
				if (items != null) {
					while (items.hasMoreElements()) {
						log.info("-- " + itemName + " : " + items.nextElement());
					}
				}
			}
			log.info("#######################################################################");
			
			if (user != null) {
                for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
					log.info("###### ROLE - " + grantedAuthority.getAuthority().substring(5));
					List<AuthDto> authDtoList = AuthMap.get(grantedAuthority.getAuthority().substring(5));
					
					if (authDtoList != null && authDtoList.size() > 0) {
						if (request.getRequestURI().startsWith("/api/system-manager/app/")) {
							String pgmId = request.getRequestURI().replace("/api/system-manager/app/", "");
							for (AuthDto authDto : authDtoList) {
								if (pgmId != null && pgmId.equals(authDto.getAPP_ID())) {
									return true;
								}
							}
						}
						
						if (request.getRequestURI().startsWith("/service/systemmanager/html/sub/")) {
							String app = request.getParameter("app");
							if (app == null) {
								return true;
							}
							
							String pgmId = request.getRequestURI().replace("/service/systemmanager/html/sub/", "");
							for (AuthDto authDto : authDtoList) {
								
								if (pgmId != null && app.equals(authDto.getAPP_ID()) && (pgmId.equals(authDto.getPGM_ID() + ".html") || (authDto.getPGM_ID_SUB() != null && pgmId.equals(authDto.getPGM_ID_SUB() + ".html")))) {
									return true;
								}
							}
						}
					}
				}
			}
			
			log.info("###### Access Denied ######");
			PrintWriter out;
			try {
				out = response.getWriter();
				response.setStatus(403);
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        String jsonStr = "{\"error\":\"Access Denied\", \"message\":\"프로그램에 대한 접근 권한이 없습니다.\", \"timestamp\":\"" + System.currentTimeMillis() + "\", \"status\":\"403\"}";
		        out.print(jsonStr);
		        out.flush();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return false;
		}
		
		return true;
	}
	
//	private String termCheck(ScvmUserDetailsImpl user) {
//		String termYn = "N";
//		String termStr = String.valueOf(DefaultInfoMap.get("INFO_AGREE_TERM"));
//		int term       = 0;
//		if (termStr != null && !"".equals(termStr) && !"null".equals(termStr)) {
//			term = Integer.valueOf(termStr);
//		}
//
//		if (term != 0) {
//			int term_y = term / 12;
//			int term_m = term % 12;
//
//			String agreeDt = user.getAgreeDt();
//			if (agreeDt != null && !"".equals(agreeDt)) {
//				int agreeYy = Integer.valueOf(agreeDt.substring(0, 4));
//				int agreeMm = Integer.valueOf(agreeDt.substring(4, 6));
//				int agreeDd = Integer.valueOf(agreeDt.substring(6));
//
//				String yy = String.valueOf(agreeYy + term_y);
//				String mm = (String.valueOf(agreeMm + term_m).length() < 2 ? "0":"") + String.valueOf(agreeMm + term_m);
//				String dd = (String.valueOf(agreeDd).length() < 2 ? "0":"") + String.valueOf(agreeDd);
//
//				agreeDt = yy + mm + dd;
//
//				Calendar calendar = Calendar.getInstance();
//				int nowYy = calendar.get(calendar.YEAR);
//				int nowMm = calendar.get(calendar.MONTH) + 1;
//				int nowDd = calendar.get(calendar.DATE);
//
//				yy = String.valueOf(nowYy);
//				mm = (String.valueOf(nowMm).length() < 2 ? "0":"") + String.valueOf(nowMm);
//				dd = (String.valueOf(nowDd).length() < 2 ? "0":"") + String.valueOf(nowDd);
//
//				String now = yy + mm + dd;
//
//				if (Integer.valueOf(now) > Integer.valueOf(agreeDt)) {
//					termYn = "Y";
//				}
//			}
//		}
//
//		return termYn;
//	}

}
