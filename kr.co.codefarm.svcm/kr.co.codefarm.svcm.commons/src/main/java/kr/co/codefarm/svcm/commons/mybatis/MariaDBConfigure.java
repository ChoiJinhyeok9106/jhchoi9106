package kr.co.codefarm.svcm.commons.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MariaDBConfigure {

	@Value("${spring.maria.mapper-locations}")
	private String mapperLocations;

//	@Value("${spring.maria.datasource.jndi-name:@null}")
//	private String jndiName;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean(name = "mariaDataSource")
//	@Profile({"dev"})
	@ConfigurationProperties(prefix = "spring.maria.datasource")
	public DataSource mariaDataSource() {
		return DataSourceBuilder.create().build();
	}

//	@Bean(name = "mysqlDataSource")
//	@Profile({"prod","staging"})
//	public DataSource mysqlJndiDataSource() {
//		return new JndiDataSourceLookup().getDataSource(this.jndiName);
//	}
//
	@Bean(name = "mariaSqlSessionFactory")
	public SqlSessionFactory gaiaSqlSessionFactory(@Qualifier("mariaDataSource") DataSource mariaDataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mariaDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperLocations));
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatisConfig.xml"));

		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "mariaSessionTemplate")
	public SqlSessionTemplate gaiaSessionTemplate(@Qualifier("mariaSqlSessionFactory") SqlSessionFactory mariaSqlSessionFactory) {
		return new SqlSessionTemplate(mariaSqlSessionFactory);
	}

}