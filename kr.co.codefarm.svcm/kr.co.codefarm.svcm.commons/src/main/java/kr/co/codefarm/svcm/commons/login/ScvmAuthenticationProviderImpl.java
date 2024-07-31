package kr.co.codefarm.svcm.commons.login;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import kr.co.codefarm.svcm.commons.login.service.ScvmUserDetailsServiceImpl;
import kr.co.codefarm.svcm.commons.utils.cipher.AES128Util;
import kr.co.codefarm.svcm.commons.utils.cipher.RSAUtil;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.net.URLEncoder;
import java.security.PrivateKey;

@Component
public class ScvmAuthenticationProviderImpl implements AuthenticationProvider {
	
	@Autowired
	private ScvmUserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		ServletRequestAttributes servletContainer = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request         = servletContainer.getRequest();
		PrivateKey privateKey              = (PrivateKey) request.getSession().getAttribute("_RSA_PRIVATE_KEY_");
		try {
			String key   = (String) request.getParameter("key");
			String exKey = (String) request.getParameter("exKey");
			String ex = AES128Util.dec(exKey);
			JSONObject chekKey = new JSONObject(ex);
			String id = RSAUtil.decryptionUsingModulusAndExponent(privateKey, authentication.getPrincipal().toString());
			String pw = RSAUtil.decryptionUsingModulusAndExponent(privateKey, authentication.getCredentials().toString());
			ScvmUserDetailsImpl user = (ScvmUserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(id, pw);
			if (user == null || user.getUserId() == null) {
				throw new BadCredentialsException("NE");
			}
			else if(Integer.parseInt(user.getLoginFailCnt()) >= 5) {
				throw new BadCredentialsException("LF");
			}
			else if(!chekKey.get("KEY").equals(key)){
				throw new BadCredentialsException("KE" + (user.getLoginFailCnt()+1));
			} else if(!(Long.parseLong(chekKey.get("EX").toString()) > System.currentTimeMillis())){
				throw new BadCredentialsException("TO");
			} else {
				if (privateKey == null) {
					throw new BadCredentialsException("NP");
				}
				else if (user != null && user.getUserId() != null && "N".equals(user.getUseYn())) {
					throw new BadCredentialsException("NU");
				} else if (user != null && user.getUserId() != null && "N".equals(user.getPassword())) {
					if(Integer.parseInt(user.getLoginFailCnt()) + 1 >= 5) 
						throw new BadCredentialsException("LF");
					else
						throw new BadCredentialsException("WP" + (user.getLoginFailCnt()+1));
				}
				user.setPwd("");
				return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			}
		} catch(BadCredentialsException e) {
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw new BadCredentialsException("TE");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
