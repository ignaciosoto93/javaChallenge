package com.marb.demo.module.security.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Because this application is also a User Info Resource Server, we expose info about the logged in user at:
 * http://localhost:8081/auth/user
 */
@RestController
@RequestMapping(path = "/auth")
public class LoggedUserInfoController {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
