package kr.co.codefarm.svcm.commons.login.dto;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ScvmUserDetailsImpl implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;

	private String ssoId;
	private String userGb;
	private String userGroupGb;
    private String userId;
	private String pwd;
	private String internalId;
	private String email;
	private String userNm;
	private String defaultAuthCd;
	private String defaultAuthNm;
	private String deptNm;
	private String pstnNm;
	private String corpId;
	private String telNo;
	private String hpNo;
	private String zipno;
	private String addr;
	private String joinDt;
	private String userStatusGb;
	private String userStatusGbnm;
	private String userPic;
	private String addCol1;
	private String addCol2;
	private String addCol3;
	private String agreeYn;
	private String agreeDt;
	private String beforeUserId;
	private String useDomain;
	private String latestLoginDttm;
	private String latestLoginsidoDomain;
	private String latestPwdChgDt;
	private String loginFailCnt;
	private String useYn;
	private long lastCheckTime;

	private Collection<GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		return this.getPwd();
	}

	@Override
	public String getUsername() {
		return this.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
