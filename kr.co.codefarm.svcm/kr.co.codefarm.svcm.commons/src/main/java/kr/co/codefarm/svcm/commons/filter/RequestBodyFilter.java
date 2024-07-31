/**
 * 
 */
package kr.co.codefarm.svcm.commons.filter;

import kr.co.codefarm.svcm.commons.wrapper.HttpRequestWrapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dki_s
 *
 */
@WebFilter(
		description = "Request Filter",
		urlPatterns = "/*",
		filterName = "RequestBodyFilter",
		asyncSupported = true
)
@Order(Ordered.LOWEST_PRECEDENCE)
@Component
public class RequestBodyFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpRequestWrapper requestWrapper     = null;
		
		if (request.getContentType() != null && request.getContentType().toLowerCase().startsWith("application/json")) {
			requestWrapper = new HttpRequestWrapper(httpServletRequest);
			
			if (httpServletRequest.getRequestURI().startsWith("/api/") || httpServletRequest.getRequestURI().startsWith("/upload/") || httpServletRequest.getRequestURI().startsWith("/jsp/")) {
				httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
				httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
				httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
				//httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
				httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
				chain.doFilter(requestWrapper, httpServletResponse);
			}else {
				chain.doFilter(requestWrapper, response);
			}
		} else {
			chain.doFilter(request, response);
		}
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
