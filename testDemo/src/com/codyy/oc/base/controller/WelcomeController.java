package com.codyy.oc.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	@RequestMapping("welcome")
	public String index(HttpServletRequest request) {
		return "index";
	}
}
