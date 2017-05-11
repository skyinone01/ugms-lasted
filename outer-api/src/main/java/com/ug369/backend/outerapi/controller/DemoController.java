package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.bean.request.UserRequest;
import com.ug369.backend.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Roy on 2017/3/8.
 * http://121.40.225.78:8080/youge-erp/
 */
@RestController
public class DemoController {

	@Autowired
	private UserService userService;

	@RequestMapping("/init/adduser")
	public String ok() {
		UserRequest request = new UserRequest();
		request.setUsername("admin");
		request.setName("系统管理员");
		request.setRole(1L);
		request.setMobile("13800000000");
		request.setPassword("abcd1234");
		request.setDepartment("平台运营部");
		userService.createOrUpdate(request);
		return "ok";
	}
}
