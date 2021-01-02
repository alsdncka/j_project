package com.jj.j_project.Config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@MapperScan(basePackages = {"com.jj.j_project.**"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DatabaseConfig {
	@Autowired

		@Bean(name = "dataSource")
		@ConfigurationProperties(prefix = "datasource")
		public DataSource dataSource() {
			
			return new HikariDataSource();
			
		}
		
		@Bean
		public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource ,ApplicationContext applicationContext) throws Exception{
			SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
			sqlSessionFactoryBean.setDataSource(dataSource);
			sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mapper/**/*.xml"));
			sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("config/mybatis-config.xml"));
			sqlSessionFactoryBean.setTypeAliasesPackage("com.jj.j_project.**");
			return sqlSessionFactoryBean.getObject();
		}
		
		@Bean(name = "sqlSession")
		public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
		
		
		
}
