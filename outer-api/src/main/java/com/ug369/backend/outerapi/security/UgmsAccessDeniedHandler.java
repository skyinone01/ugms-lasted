package com.ug369.backend.outerapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.exception.UgmsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Roy on 2017/3/18.
 */
@Component
public class UgmsAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {

        if (!httpServletResponse.isCommitted()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter writer = httpServletResponse.getWriter();
            writer.print(objectMapper.writeValueAsString(new BasicResponse(UgmsStatus.AUTH_FAILED,
                    "没有权限: " + httpServletRequest.getRequestURI())));
            writer.flush();
            writer.close();
        }
    }

}
