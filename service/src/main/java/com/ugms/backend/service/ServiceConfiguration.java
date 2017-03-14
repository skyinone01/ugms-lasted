package com.ugms.backend.service;

import com.ugms.backend.service.repository.mysql.MysqlRepositoryMarker;
import com.ugms.backend.service.repository.rdbsupport.RDBRepositoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Roy on 2017/3/18.
 */
@Configuration
@EnableAutoConfiguration(exclude = MongoAutoConfiguration.class)
@EnableJpaRepositories(basePackageClasses = MysqlRepositoryMarker.class,
		repositoryFactoryBeanClass = RDBRepositoryBean.class)
public class ServiceConfiguration {

}
