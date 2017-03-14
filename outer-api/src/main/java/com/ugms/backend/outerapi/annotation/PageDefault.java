package com.ugms.backend.outerapi.annotation;

import java.lang.annotation.*;

/**
 * Created by Roy on 2017/3/8.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageDefault {

    //页码
    int page() default 1;

    //每页条目数
    int size() default 20;
}
