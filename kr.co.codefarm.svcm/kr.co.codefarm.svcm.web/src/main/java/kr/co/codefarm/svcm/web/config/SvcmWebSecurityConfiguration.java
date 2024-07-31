package kr.co.codefarm.svcm.web.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import kr.co.codefarm.svcm.commons.login.ScvmAuthenticationProviderImpl;
import kr.co.codefarm.svcm.commons.login.handler.ScvmAccessDeniedHandler;
import kr.co.codefarm.svcm.commons.login.handler.ScvmLoginFailureHandler;
import kr.co.codefarm.svcm.commons.login.handler.ScvmLoginSuccessHandler;
import kr.co.codefarm.svcm.web.sso.filter.SSOFilter;
import kr.co.codefarm.svcm.web.sso.handler.SSOLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SvcmWebSecurityConfiguration extends WebSecurityConfigurerAdapter  {

	@Value("${enable-csrf}")
	private boolean csrfEnabled;

	@Value("${v-context.login}")
	private String loginContext;

	@Value("${use-sso}")
	private boolean ssoEnabled;

	@Autowired
	private ScvmAuthenticationProviderImpl scvmAuthProvider;

	@Autowired
	private ScvmLoginSuccessHandler scvmLoginSuccessHandler;

	@Autowired
	private ScvmLoginFailureHandler scvmLoginFailureHandler;

	@Autowired
	private ScvmAccessDeniedHandler scvmAccessDeniedHandler;

	@Autowired
	private SSOLogoutSuccessHandler sSOLogoutSuccessHandler;

	@Autowired
	private SSOFilter ssoFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String loginPage = "/html/main" + this.loginContext + "login.html";
		http.addFilterAfter(this.ssoFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin();
		http.authorizeRequests()
			.antMatchers("/.well-known/**", "/"
						, "/assets/css/**"
						, "/assets/js/*"
						, "/assets/js/vendor/**"
						, "/assets/js/cf_component/*"
						, "/assets/js/cf_component/template/*"
						, "/images/**"
						, "/index.html"
						, "/find/*"
					    , "/change/*"
					    , "/mailauth/*"
					    , "/sso/**"
						, "/commons/file/upload/**"
						, "/commons/file"
						, "/error/**"
						, "/assets/js/cryptojs/**"
						, "/favicon.ico"
						, "/api/**"
						, "/comm/**"
						, "/terms/*"
						, "/system-manager/service/initList"
						, "/publickeys"
						, "/decrypt"
						, "/html/main" + this.loginContext + "login.html"
						, "/html/main" + this.loginContext + "sso.html"
						, "/html/main/signup.html"
						, "/html/main/signup_type.html"
						, "/html/main/signup_complete.html"
						, "/html/main/signup_auth.html"
						, "/html/main/signup_terms.html"
						, "/html/main/findid.html"
						, "/html/main/findpw.html"
						, "/html/main/auth.html"
						, "/upload/**"
						, "/download/**"
						, "/index.html"
						, "/commons/file/upload/**"
						, "/commons/file"
						, "/error/**"
						, "/uploads/**"
						, "/assets/js/cryptojs/**"
						, "/assets/css/login.css"

						//공통
						, "/assets/js/vendor/**"
						, "/assets/css/**"
						, "/service/design/assets/**"
						, "/service/design/images/**"
						, "/app-assets/js/api/systemmanager/defaultinfoApi.api.js"
						, "/api/system-manager/default-info/defaultinfo"
						, "/app-assets/js/api/systemmanager/etc.api.js"
						, "/assets/js/axios.common.js"
						//기본정보
						, "/system-manager/default-info/representative"
						//포트 폴리오
						, "/app-assets/js/api/commons/email.api.js"					// 이포트폴리오 외부 - 메일
						, "/app-assets/js/api/systemmanager/userApi.api.js"			// 이포트폴리오 외부 - 사용자 정보 조회
						, "/commons/mail/send"										// 이포트폴리오 외부 - 메일 보내기
						, "/assets/css/vue/vue2-perfect-scrollbar.min.css"			// 이포트폴리오 외부 - 스크롤바
						, "/assets/js/vendor/vue/vue2-perfect-scrollbar.umd.min.js"	// 이포트폴리오 외부 - 스크롤바
						, "/additional/farmboardApi/**"

						/* NEW 포트폴리오 css */
						, "/assets/css/reset.css"
						, "/assets/css/feather.css"
						, "/assets/css/xcloud-icon.css"
						, "/assets/css/jui/jui-ui.classic.css"
						, "/service/design/assets/css/pf-complete2.css"
						, "/service/design/assets/css/pf-complete.css"
						, "/service/design/assets/css/portfolio.css"
						, "/assets/css/vue/vue-toast-notification.css"
						, "/assets/css/tui/tui-editor.css"
						, "/assets/css/tui/tui-editor-contents.css"
						, "/assets/css/tui/codemirror.css"
						, "/assets/css/tui/github.min.css"
						, "/assets/css/tui/tui-color-picker.css"
						, "/assets/css/vue/vue2-perfect-scrollbar.min.css"
						, "/assets/css/slick.css"
						, "/assets/js/vendor/slick.min.js"

						/* NEW 포트폴리오 api(작성자 : 최대건(2019-09-30)) */
						, "/assets/js/cdf_library.js"
						, "/assets/js/common.js"
						, "/assets/js/cf_component/CommcodeSet.js"
						, "/assets/js/cf_component/CustomcodeSet.js"
						, "/app-assets/js/api/systemmanager/userApi.api.js"
						, "/app-assets/js/api/systemmanager/defaultinfoApi.api.js"
						, "/app-assets/js/api/additional/farmboardApi.api.js"

						, "/assets/js/cf_component/Dataset.js"
						, "/assets/js/cf_component/ModalSet.js"
						, "/assets/js/cf_component/Pagingset.js"


						, "/find/*"
						, "/change/*"
						, "/mailauth/*"
						, "/sso/**"
						, "/session-info"
						, "/api/is-session"
						, "/assets/js/cf_component/popup/systemmanager/user/corppopupTemplate.js"
						, "/service/additional/images/common/**"
						, "/info/**"
						, "/system-manager/service/codeUseList"

			).permitAll()
			.antMatchers("/**").authenticated()
		.and()
			.formLogin()
			.loginPage(loginPage)
			.loginProcessingUrl("/login")
			.failureHandler(this.scvmLoginFailureHandler)
			.successHandler(this.scvmLoginSuccessHandler)
			.usernameParameter("uid")
			.passwordParameter("upw")
		.and()
			.logout().logoutUrl("/logout")
			.deleteCookies("JSESSIONID",ScvmLoginSuccessHandler.ACCESS_COOKIE_NAME)
	        .clearAuthentication(true)
			.logoutSuccessHandler(sSOLogoutSuccessHandler)
		.and()
			.authenticationProvider(this.scvmAuthProvider)
			.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint(loginPage))
			.accessDeniedHandler(this.scvmAccessDeniedHandler);

		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
