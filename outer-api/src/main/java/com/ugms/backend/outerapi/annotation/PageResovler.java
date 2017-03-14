package com.ugms.backend.outerapi.annotation;

import com.ugms.backend.bean.exception.UserException;
import com.ugms.backend.bean.base.request.PageRequest;
import com.ugms.backend.bean.exception.UgmsStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by Roy on 2017/3/8.
 */
@Component
public class PageResovler implements HandlerMethodArgumentResolver {

    @Value("${ugms.pager.param.page}")
    String pageParam;

    @Value("${ugms.pager.param.size}")
    String sizeParam;

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == PageRequest.class;
    }

    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws UserException {

        int page;
        int size;

        //1.无注解
        if (methodParameter.getParameterAnnotation(PageDefault.class) == null) {

            try {
                page = Integer.parseInt(webRequest.getParameter(pageParam));
            } catch (NumberFormatException e) {
                throw new UserException(UgmsStatus.BAD_REQUEST, "invalid param " + pageParam);
            }

            try {
                size = Integer.parseInt(webRequest.getParameter(sizeParam));
            } catch (NumberFormatException e) {
                throw new UserException(UgmsStatus.BAD_REQUEST, "invalid param " + sizeParam);
            }
        } else {
            //2.有注解
            try {
                page = Integer.parseInt(webRequest.getParameter(pageParam));
            } catch (NumberFormatException e) {
                page = methodParameter.getParameterAnnotation(PageDefault.class).page();
            }

            try {
                size = Integer.parseInt(webRequest.getParameter(sizeParam));
            } catch (NumberFormatException e) {
                size = methodParameter.getParameterAnnotation(PageDefault.class).size();
            }
        }
        if (page < 1 || size < 1) {
            throw new UserException(UgmsStatus.BAD_REQUEST, "invalid page params");
        }
        return PageRequest.of(page, size);
    }
}
