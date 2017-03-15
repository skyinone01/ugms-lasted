package com.ug369.backend.outerapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.exception.UgmsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Roy on 2017/3/8.
 */
@Component
public class UgmsAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        if (!(e instanceof InsufficientAuthenticationException)) {

            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter writer = httpServletResponse.getWriter();
            writer.print(objectMapper
                    .writeValueAsString(new BasicResponse(UgmsStatus.AUTH_FAILED, "用户名密码错误")));
            writer.flush();
            writer.close();
        }
    }
}
