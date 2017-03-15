package com.ug369.backend.outerapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Roy on 2017/3/8.
 */
@RestController
public class DemoController {

	@RequestMapping("/test")
	public String ok() {
		return "ok";
	}
}
