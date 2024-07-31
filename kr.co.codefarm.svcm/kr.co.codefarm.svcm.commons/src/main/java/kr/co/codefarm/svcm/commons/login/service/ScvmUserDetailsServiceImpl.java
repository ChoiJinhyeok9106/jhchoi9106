package kr.co.codefarm.svcm.commons.login.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.co.codefarm.svcm.commons.login.dto.ScvmUserDetailsImpl;
import lombok.Setter;

@Service
public class ScvmUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired 
	private ScvmLoginService loginService;
	
	public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
		ServletRequestAttributes servletContainer = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request         = servletContainer.getRequest();
			
		HashMap<String, Object> param = new HashMap<>();

//		if(request.getParameter("loginType") != null)
//			param.put("pLoginGb", request.getParameter("loginType"));
		
		param.put("USER_ID", username);
		param.put("PWD", password);
		List<Map<String, Object>> userList = loginService.getUser(param);
		
		ScvmUserDetailsImpl userDetailsImpl = new ScvmUserDetailsImpl();
		if (userList.size() > 0) {
			userDetailsImpl.setSsoId("");
			userDetailsImpl.setUserGb((String) userList.get(0).get("USER_GB"));
			userDetailsImpl.setUserGroupGb((String) userList.get(0).get("USER_GROUP_GB"));
			userDetailsImpl.setUserId((String) userList.get(0).get("USER_ID"));
			userDetailsImpl.setPwd((String) userList.get(0).get("PWD"));
			userDetailsImpl.setInternalId((String) userList.get(0).get("INTERNAL_ID"));
			userDetailsImpl.setEmail((String) userList.get(0).get("EMAIL"));
			userDetailsImpl.setUserNm((String) userList.get(0).get("USER_NM"));
			userDetailsImpl.setDefaultAuthCd((String) userList.get(0).get("DEFAULT_AUTH_CD"));
			userDetailsImpl.setDefaultAuthNm((String) userList.get(0).get("DEFAULT_AUTH_NM"));
			userDetailsImpl.setCorpId((String) userList.get(0).get("CORP_ID"));
			userDetailsImpl.setDeptNm((String) userList.get(0).get("DEPT_NM"));
			userDetailsImpl.setPstnNm((String) userList.get(0).get("PSTN_NM"));
            userDetailsImpl.setUseYn((String) userList.get(0).get("USE_YN"));
			userDetailsImpl.setTelNo((String) userList.get(0).get("TEL_NO"));
			userDetailsImpl.setHpNo((String) userList.get(0).get("HP_NO"));
			userDetailsImpl.setZipno((String) userList.get(0).get("ZIPNO"));
			userDetailsImpl.setAddr((String) userList.get(0).get("ADDR"));
			userDetailsImpl.setJoinDt((String) userList.get(0).get("JOIN_DT"));
			userDetailsImpl.setUserStatusGb((String) userList.get(0).get("USER_STATUS_GB"));
			userDetailsImpl.setUserStatusGbnm((String) userList.get(0).get("USER_STATUS_GBNM"));
			userDetailsImpl.setUserPic((String) userList.get(0).get("USER_PIC"));
			userDetailsImpl.setAddCol1((String) userList.get(0).get("ADD_COL1"));
			userDetailsImpl.setAddCol2((String) userList.get(0).get("ADD_COL2"));
			userDetailsImpl.setAddCol3((String) userList.get(0).get("ADD_COL3"));
			userDetailsImpl.setAgreeYn((String) userList.get(0).get("AGREE_YN"));
			userDetailsImpl.setAgreeDt((String) userList.get(0).get("AGREE_DT"));
			userDetailsImpl.setLoginFailCnt((String) userList.get(0).get("LOGIN_FAIL_CNT"));
			userDetailsImpl.setLatestPwdChgDt((String) userList.get(0).get("LATEST_PWD_CHG_DT"));
			userDetailsImpl.setLastCheckTime(System.currentTimeMillis());
			userDetailsImpl.setAuthorities(getAuthorities(param));
		}
       
		return userDetailsImpl;
	}


	public UserDetails loadUserByUsername(String username, String password, String beforeUserId) throws UsernameNotFoundException {
		HashMap<String, Object> param = new HashMap<>();
		param.put("USER_ID", username);
		param.put("PWD", password);

		List<Map<String, Object>> userList = loginService.getUser(param);

		ScvmUserDetailsImpl userDetailsImpl = new ScvmUserDetailsImpl();

		if (userList.size() > 0) {
			userDetailsImpl.setSsoId("");
			userDetailsImpl.setUserGb((String) userList.get(0).get("USER_GB"));
			userDetailsImpl.setUserGroupGb((String) userList.get(0).get("USER_GROUP_GB"));
			userDetailsImpl.setUserId((String) userList.get(0).get("USER_ID"));
			userDetailsImpl.setPwd((String) userList.get(0).get("PWD"));
			userDetailsImpl.setInternalId((String) userList.get(0).get("INTERNAL_ID"));
			userDetailsImpl.setEmail((String) userList.get(0).get("EMAIL"));
			userDetailsImpl.setUserNm((String) userList.get(0).get("USER_NM"));
			userDetailsImpl.setDefaultAuthCd((String) userList.get(0).get("DEFAULT_AUTH_CD"));
			userDetailsImpl.setDefaultAuthNm((String) userList.get(0).get("DEFAULT_AUTH_NM"));
			userDetailsImpl.setCorpId((String) userList.get(0).get("CORP_ID"));
			userDetailsImpl.setDeptNm((String) userList.get(0).get("DEPT_NM"));
			userDetailsImpl.setPstnNm((String) userList.get(0).get("PSTN_NM"));
            userDetailsImpl.setUseYn((String) userList.get(0).get("USE_YN"));
			userDetailsImpl.setTelNo((String) userList.get(0).get("TEL_NO"));
			userDetailsImpl.setHpNo((String) userList.get(0).get("HP_NO"));
			userDetailsImpl.setZipno((String) userList.get(0).get("ZIPNO"));
			userDetailsImpl.setAddr((String) userList.get(0).get("ADDR"));
			userDetailsImpl.setJoinDt((String) userList.get(0).get("JOIN_DT"));
			userDetailsImpl.setUserStatusGb((String) userList.get(0).get("USER_STATUS_GB"));
			userDetailsImpl.setUserStatusGbnm((String) userList.get(0).get("USER_STATUS_GBNM"));
			userDetailsImpl.setUserPic((String) userList.get(0).get("USER_PIC"));
			userDetailsImpl.setAddCol1((String) userList.get(0).get("ADD_COL1"));
			userDetailsImpl.setAddCol2((String) userList.get(0).get("ADD_COL2"));
			userDetailsImpl.setAddCol3((String) userList.get(0).get("ADD_COL3"));
			userDetailsImpl.setAgreeYn((String) userList.get(0).get("AGREE_YN"));
			userDetailsImpl.setAgreeDt((String) userList.get(0).get("AGREE_DT"));
			userDetailsImpl.setLoginFailCnt((String) userList.get(0).get("LOGIN_FAIL_CNT"));
			userDetailsImpl.setLatestPwdChgDt((String) userList.get(0).get("LATEST_PWD_CHG_DT"));
			userDetailsImpl.setLastCheckTime(System.currentTimeMillis());
			userDetailsImpl.setBeforeUserId(beforeUserId);

			userDetailsImpl.setAuthorities(getAuthorities(param));
		}

		return userDetailsImpl;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HashMap<String, Object> param = new HashMap<>();
		param.put("USER_ID", username);
		
		List<Map<String, Object>> userList = loginService.getUser(param);
		
		ScvmUserDetailsImpl userDetailsImpl = new ScvmUserDetailsImpl();

		if (userList.size() > 0) {
			userDetailsImpl.setUserGb((String) userList.get(0).get("USER_GB"));
			userDetailsImpl.setUserGroupGb((String) userList.get(0).get("USER_GROUP_GB"));
			userDetailsImpl.setUserId((String) userList.get(0).get("USER_ID"));
			userDetailsImpl.setPwd((String) userList.get(0).get("PWD"));
			userDetailsImpl.setInternalId((String) userList.get(0).get("INTERNAL_ID"));
			userDetailsImpl.setEmail((String) userList.get(0).get("EMAIL"));
			userDetailsImpl.setUserNm((String) userList.get(0).get("USER_NM"));
			userDetailsImpl.setDefaultAuthCd((String) userList.get(0).get("DEFAULT_AUTH_CD"));
			userDetailsImpl.setDefaultAuthNm((String) userList.get(0).get("DEFAULT_AUTH_NM"));
			userDetailsImpl.setCorpId((String) userList.get(0).get("CORP_ID"));
			userDetailsImpl.setDeptNm((String) userList.get(0).get("DEPT_NM"));
			userDetailsImpl.setPstnNm((String) userList.get(0).get("PSTN_NM"));
            userDetailsImpl.setUseYn((String) userList.get(0).get("USE_YN"));
			userDetailsImpl.setTelNo((String) userList.get(0).get("TEL_NO"));
			userDetailsImpl.setHpNo((String) userList.get(0).get("HP_NO"));
			userDetailsImpl.setZipno((String) userList.get(0).get("ZIPNO"));
			userDetailsImpl.setAddr((String) userList.get(0).get("ADDR"));
			userDetailsImpl.setJoinDt((String) userList.get(0).get("JOIN_DT"));
			userDetailsImpl.setUserStatusGb((String) userList.get(0).get("USER_STATUS_GB"));
			userDetailsImpl.setUserStatusGbnm((String) userList.get(0).get("USER_STATUS_GBNM"));
			userDetailsImpl.setUserPic((String) userList.get(0).get("USER_PIC"));
			userDetailsImpl.setAddCol1((String) userList.get(0).get("ADD_COL1"));
			userDetailsImpl.setAddCol2((String) userList.get(0).get("ADD_COL2"));
			userDetailsImpl.setAddCol3((String) userList.get(0).get("ADD_COL3"));
			userDetailsImpl.setAgreeYn((String) userList.get(0).get("AGREE_YN"));
			userDetailsImpl.setAgreeDt((String) userList.get(0).get("AGREE_DT"));
			userDetailsImpl.setLoginFailCnt((String) userList.get(0).get("LOGIN_FAIL_CNT"));
			userDetailsImpl.setLatestPwdChgDt((String) userList.get(0).get("LATEST_PWD_CHG_DT"));
			userDetailsImpl.setLastCheckTime(System.currentTimeMillis());
			
			userDetailsImpl.setAuthorities(getAuthorities(param));
		}
		return userDetailsImpl;
	}
	
	public UserDetails loadUserByUsername(String username, boolean tokenYn) throws UsernameNotFoundException {
		HashMap<String, Object> param = new HashMap<>();
		param.put("USER_ID", username);
		
		List<Map<String, Object>> userList = loginService.getTokenUser(param);
		
		ScvmUserDetailsImpl userDetailsImpl = new ScvmUserDetailsImpl();

		if (userList.size() > 0) {
			param.put("USER_ID", userList.get(0).get("USER_ID"));
			userDetailsImpl.setUserGb((String) userList.get(0).get("USER_GB"));
			userDetailsImpl.setUserGroupGb((String) userList.get(0).get("USER_GROUP_GB"));
			userDetailsImpl.setUserId((String) userList.get(0).get("USER_ID"));
			userDetailsImpl.setPwd((String) userList.get(0).get("PWD"));
			userDetailsImpl.setInternalId((String) userList.get(0).get("INTERNAL_ID"));
			userDetailsImpl.setEmail((String) userList.get(0).get("EMAIL"));
			userDetailsImpl.setUserNm((String) userList.get(0).get("USER_NM"));
			userDetailsImpl.setDefaultAuthCd((String) userList.get(0).get("DEFAULT_AUTH_CD"));
			userDetailsImpl.setDefaultAuthNm((String) userList.get(0).get("DEFAULT_AUTH_NM"));
			userDetailsImpl.setCorpId((String) userList.get(0).get("CORP_ID"));
			userDetailsImpl.setDeptNm((String) userList.get(0).get("DEPT_NM"));
			userDetailsImpl.setPstnNm((String) userList.get(0).get("PSTN_NM"));
            userDetailsImpl.setUseYn((String) userList.get(0).get("USE_YN"));
			userDetailsImpl.setTelNo((String) userList.get(0).get("TEL_NO"));
			userDetailsImpl.setHpNo((String) userList.get(0).get("HP_NO"));
			userDetailsImpl.setZipno((String) userList.get(0).get("ZIPNO"));
			userDetailsImpl.setAddr((String) userList.get(0).get("ADDR"));
			userDetailsImpl.setJoinDt((String) userList.get(0).get("JOIN_DT"));
			userDetailsImpl.setUserStatusGb((String) userList.get(0).get("USER_STATUS_GB"));
			userDetailsImpl.setUserStatusGbnm((String) userList.get(0).get("USER_STATUS_GBNM"));
			userDetailsImpl.setUserPic((String) userList.get(0).get("USER_PIC"));
			userDetailsImpl.setAddCol1((String) userList.get(0).get("ADD_COL1"));
			userDetailsImpl.setAddCol2((String) userList.get(0).get("ADD_COL2"));
			userDetailsImpl.setAddCol3((String) userList.get(0).get("ADD_COL3"));
			userDetailsImpl.setAgreeYn((String) userList.get(0).get("AGREE_YN"));
			userDetailsImpl.setAgreeDt((String) userList.get(0).get("AGREE_DT"));
			userDetailsImpl.setLoginFailCnt((String) userList.get(0).get("LOGIN_FAIL_CNT"));
			userDetailsImpl.setLatestPwdChgDt((String) userList.get(0).get("LATEST_PWD_CHG_DT"));
			userDetailsImpl.setLastCheckTime(System.currentTimeMillis());
			
			userDetailsImpl.setAuthorities(getAuthorities(param));
		}
		return userDetailsImpl;
	}
	
	public Collection<GrantedAuthority> getAuthorities(HashMap<String, Object> param) {
		List<GrantedAuthority> roles = new ArrayList<>();
        List<Map<String, Object>> roleList = loginService.getRole(param);
        for (Map<String, Object> role : roleList) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + (String) role.get("ROLE_CD")));
        }
		return roles;
	}

}
