package com.ugms.backend.utils;

import org.springframework.context.ApplicationContext;

/**
 * Created by Roy on 2017/3/8.
 */
public class ContextUtils {
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
