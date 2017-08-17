package org.tabchanj.aladdin.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login/")
public class LoginController {

	@RequestMapping("login")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}
}
