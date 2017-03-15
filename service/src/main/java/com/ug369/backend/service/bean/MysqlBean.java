package com.ug369.backend.service.bean;

import com.ug369.backend.service.repository.rdbsupport.component.MybatisData;
import com.ug369.backend.service.repository.rdbsupport.component.MybatisDataBase;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL57InnoDBDialect;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by Roy on 2017/3/9.
 */
@Configuration
public class MysqlBean {

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		bean.setDataSource(dataSource);
		bean.setConfigLocation(resolver.getResource("classpath:mybatis.xml"));
		bean.setMapperLocations(resolver.getResources("classpath:mybatis/*.xml"));
		return bean;
	}
	@Bean
	public MybatisData mybatisData(SqlSessionFactory sqlSessionFactory, Dialect dialect) throws Exception {
		MybatisDataBase mybatisDataBase = new MybatisDataBase();
		mybatisDataBase.setSqlSessionFactory(sqlSessionFactory);
		mybatisDataBase.setDialect(dialect);
		mybatisDataBase.initialize();
		return mybatisDataBase;
	}
	@Bean
	public Dialect dialect() {
		return new MySQL57InnoDBDialect();
	}
}
