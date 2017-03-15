package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.bean.request.UserRequest;
import com.ug369.backend.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Roy on 2017/3/8.
 */
@RestController
public class DemoController {

	@Autowired
	private UserService userService;

	@RequestMapping("/test/adduser")
	public String ok() {
		UserRequest request = new UserRequest();
		request.setUsername("skyinone");
		request.setPassword("abcd1234");
		userService.createOrUpdate(request);
		return "ok";
	}
}
