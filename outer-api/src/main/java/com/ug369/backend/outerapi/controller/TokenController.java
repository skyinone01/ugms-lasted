package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.bean.response.TokenResponse;
import com.ug369.backend.service.entity.mysql.User;
import com.ug369.backend.service.service.UserService;
import com.ug369.backend.utils.AesUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.ByteBuffer;
import java.time.Instant;

/**
 * Created by Roy on 2017/3/8.
 */
@RestController
public class TokenController {
	@Autowired
	private AesUtils aesUtils;
	@Autowired
	private UserService userService;

	@Value("${outer.token.aes-key}")
	private String key;
	@Value("${outer.token.timeout}")
	private int timeout;

	/**
	 * 获取用户登录token
     */
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public DataResponse<TokenResponse> getToken(@RequestParam("username") String username,
												@RequestParam("password") String password, HttpServletRequest request,HttpServletResponse hresponse) {
		User user = userService.getUser(username, password);
		ByteBuffer byteBuffer = ByteBuffer.allocate(32);
		byteBuffer.putLong(Instant.now().toEpochMilli());
		byteBuffer.putLong(user.getId());
		byteBuffer.putLong(timeout);
		byteBuffer.putLong(RandomUtils.nextLong(0, Long.MAX_VALUE));
		byte[] tokenBytes = aesUtils.encryptToken(byteBuffer.array(), key);
		TokenResponse response = new TokenResponse();
		response.setToken(Base64.encodeBase64URLSafeString(tokenBytes));
		response.setTimeout(timeout);
		Cookie cookie = new Cookie("token",response.getToken());
		cookie.setMaxAge(30);
		hresponse.addCookie(cookie);
		return new DataResponse<>(response);
	}

	/**
	 * 获取用户登出
	 */
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public BasicResponse getToken(HttpServletRequest request, HttpServletResponse hresponse) {

		String token = request.getHeader("token");
		if(!StringUtils.isEmpty(token)){
			Cookie cookie = new Cookie("token",token);
			cookie.setMaxAge(0);
			hresponse.addCookie(cookie);
		}
		return BasicResponse.success();
	}
}
