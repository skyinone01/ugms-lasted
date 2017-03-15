package com.ug369.backend.outerapi.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by Roy on 2017/3/8.
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    UserResolver userResolver;
    @Autowired
    PageResovler pageResovler;

    //注册注解
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userResolver);
        argumentResolvers.add(pageResovler);
    }

}
