package kr.co.codefarm.svcm.web;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import kr.co.codefarm.svcm.commons.utils.JwtUtil;

//import com.ksign.access.wrapper.filter.SSOWrapperFilter;

@EnableScheduling
@ComponentScan("kr.co.codefarm.svcm")
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class SvcmSpringBootApplication extends SpringBootServletInitializer {
	
	@Value("${keyfile}")
	private String _KEYFILE;
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SvcmSpringBootApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SvcmSpringBootApplication.class, args);
	}
	
//	@Bean
//	public void setJwtKey() {
//		FileReader fr = null;
//		BufferedReader reader = null;
//		try {
//			fr = new FileReader(_KEYFILE);
//			reader = new BufferedReader(fr);
//			StringBuilder stringBuilder = new StringBuilder();
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				stringBuilder.append(line);
//			}
//
//			JwtUtil.setKey(stringBuilder.toString());
//		} catch (IOException | NullPointerException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(reader != null)
//					reader.close();
//				if(fr != null)
//					fr.close();
//			} catch (IOException | NullPointerException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}