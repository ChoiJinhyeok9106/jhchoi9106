package kr.co.codefarm.svcm.commons.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class CommonMainTxManager {
	
	@Value("${spring.databse.main}")
	private String dbTemplateName;
	
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(ApplicationContext context) {
		this.dataSource = (DataSource) context.getBean(dbTemplateName + "DataSource");
	}
	
	@Bean(name = "mainTxManager")
	@Primary
    public PlatformTransactionManager mainTxManager() {
		return new DataSourceTransactionManager(this.dataSource);
	}

}
