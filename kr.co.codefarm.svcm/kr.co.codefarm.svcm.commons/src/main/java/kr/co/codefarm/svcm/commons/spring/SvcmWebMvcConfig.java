package kr.co.codefarm.svcm.commons.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import kr.co.codefarm.svcm.commons.component.ExcelReaderComponent;
import kr.co.codefarm.svcm.commons.interceptor.AlarmInterceptor;
import kr.co.codefarm.svcm.commons.interceptor.AuthenticationInterceptor;
import kr.co.codefarm.svcm.commons.mapper.CustomMapper;
import kr.co.codefarm.svcm.commons.properties.SystemProp;
import kr.co.codefarm.svcm.commons.resolvers.ParameterMapArgumentResolver;

@Configuration
@EnableConfigurationProperties(SystemProp.class)
@EnableWebMvc
public class SvcmWebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private SystemProp prop;
	
	@Value("${homedir}")
	private String _HOMEDIR;
	
	@Value("${mail.host}")
	private String _MAIL_HOST;
	
	@Value("${mail.port}")
	private String _MAIL_PORT;
	
	@Value("${mail.username}")
	private String _MAIL_USERNAME;
	
	@Value("${mail.password}")
	private String _MAIL_PASSWORD;
	
	@Value("${mail.properties}")
	private List<String> _MAIL_properties;
	
	@Autowired
	private ExcelReaderComponent excelReaderComponent;
	
	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;
	
	@Autowired
	private AlarmInterceptor alarmInterceptor;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		.allowedHeaders("*")
		.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
		//.allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method","Access-Control-Request-Headers")
        //.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
        .allowCredentials(false)
        .maxAge(3600);
		
		registry.addMapping("/upload/**")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		.allowedHeaders("*")
		.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
		//.allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method","Access-Control-Request-Headers")
        //.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
		.allowCredentials(false)
        .maxAge(3600);
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/html/");
		internalResourceViewResolver.setRedirectHttp10Compatible(false);
	    registry.viewResolver(internalResourceViewResolver);
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resourceLocation = this._HOMEDIR.replaceAll("\\\\", "/");
        resourceLocation = "file://" + resourceLocation.substring(resourceLocation.indexOf(":") + 1) + "/upload/";
        
        registry.addResourceHandler("/**").addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/upload/**").addResourceLocations(resourceLocation);
    }
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new ParameterMapArgumentResolver(this.prop, this.excelReaderComponent, this._HOMEDIR));
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		CustomMapper customMapper = new CustomMapper();
		converters.add(new MappingJackson2HttpMessageConverter(customMapper));
	}
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.authenticationInterceptor).addPathPatterns("/**");
		
		List<String> excludePatterns = new ArrayList<>();
		excludePatterns.add("/find/*");
		excludePatterns.add("/change/*");
		excludePatterns.add("/mailauth/*");
		excludePatterns.add("/api/system-manager/user/find/*");
		excludePatterns.add("/api/system-manager/user/change/*");
		excludePatterns.add("/html/main/login.html");
		excludePatterns.add("/html/main/signup.html");
		excludePatterns.add("/html/main/signup_type.html");
		excludePatterns.add("/html/main/signup_complete.html");
		excludePatterns.add("/html/main/signup_auth.html");
		excludePatterns.add("/html/main/signup_terms.html");
		excludePatterns.add("/html/main/findid.html");
		excludePatterns.add("/html/main/findpw.html");
		excludePatterns.add("/html/main/auth.html");
		
		registry.addInterceptor(this.alarmInterceptor).addPathPatterns("/**").excludePathPatterns(excludePatterns);
	}
	
//	@Bean
//	public ServletRegistrationBean<HttpServlet> dispatcherServlet() {
//		ServletRegistrationBean<HttpServlet> registration = new ServletRegistrationBean<>();
//		registration.set (new DispatcherServlet());
//		DispatcherServlet dispatcherServlet = new DispatcherServlet();
//
//		registration.addUrlMappings("/");
//		registration.setLoadOnStartup(1);
//		registration.setAsyncSupported(true);
//		return registration;
//	}
	
	@Bean 
	public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	}
	
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		
		javaMailSenderImpl.setHost(this._MAIL_HOST);
		javaMailSenderImpl.setPort(Integer.valueOf(this._MAIL_PORT));
		javaMailSenderImpl.setUsername(this._MAIL_USERNAME);
		javaMailSenderImpl.setPassword(this._MAIL_PASSWORD);
		javaMailSenderImpl.setDefaultEncoding("UTF-8");
		
		if (this._MAIL_properties.size() > 0) {
			Properties javaMailProperties = new Properties();
			for (String prop : this._MAIL_properties) {
				javaMailProperties.put(prop.split(":")[0], prop.split(":")[1]);
			}			
			javaMailSenderImpl.setJavaMailProperties(javaMailProperties);
		}
		
		return javaMailSenderImpl;
	}
}
