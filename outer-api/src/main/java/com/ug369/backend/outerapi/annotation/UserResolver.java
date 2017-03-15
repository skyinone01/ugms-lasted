package com.ug369.backend.outerapi.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by Roy on 2017/3/8.
 */
@Component
public class UserResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(UserInjected.class) != null;
    }

    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) {

        Object object = webRequest.getUserPrincipal();
        if (object != null) {
            return ((Authentication) object).getPrincipal();
        } else {
            throw new AccessDeniedException("");
        }
    }
}