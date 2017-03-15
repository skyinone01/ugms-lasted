package com.ug369.backend.outerapi.security;

import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.service.entity.mysql.User;
import com.ug369.backend.service.service.UserService;
import com.ug369.backend.utils.TokenUtils;
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

/**
 * Created by roy on 2017/3/7.
 */

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
	TokenUtils tokenUtils;

	@Autowired
	UserService userService;

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
            throws AuthenticationException, IOException, ServletException {

	    String token = request.getParameter("token");
	    if (token != null ) {
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
		    }
	    }

        filter.doFilter(request, response);
    }
}
