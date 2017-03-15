package com.ug369.backend.outerapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.service.entity.mysql.User;
import com.ug369.backend.service.service.UserService;
import com.ug369.backend.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by roy on 2017/3/7.
 */

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
	TokenUtils tokenUtils;

	@Autowired
	UserService userService;

	@Autowired
	ObjectMapper objectMapper;

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
            throws AuthenticationException, IOException, ServletException {

	    if (!request.getRequestURI().equals("/token")){
			String token = request.getHeader("token");
			if (!StringUtils.isEmpty(token) ) {
				try {
					Long userId = tokenUtils.validate(token);
					User user = userService.findById(userId);
					if (user != null) {
						if (user.getRole() != 0) {
							SecurityContextHolder.getContext().setAuthentication(
									new UsernamePasswordAuthenticationToken(user, user, AuthorityUtils
											.createAuthorityList("ROLE_" + user.getRole())));
						}
					}

				} catch (NumberFormatException | UserException e) {
					e.printStackTrace();
				}
			}else {

				if (!request.getMethod().equals("OPTIONS")){
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					PrintWriter writer = response.getWriter();
					writer.print(objectMapper
							.writeValueAsString(new BasicResponse(UgmsStatus.AUTH_FAILED, "没有认证信息")));
					writer.flush();
					writer.close();
					return;
				}
			}
		}
		filter.doFilter(request, response);

    }
}
