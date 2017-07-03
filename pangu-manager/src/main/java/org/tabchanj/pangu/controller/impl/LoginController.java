package org.tabchanj.pangu.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/")
public class LoginController {

	@RequestMapping("login")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}
}
