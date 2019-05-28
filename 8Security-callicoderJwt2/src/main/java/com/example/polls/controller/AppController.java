package com.example.polls.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/mylogin")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/admin")
	public String admin(HttpServletRequest request) {
		Authentication myauthentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(myauthentication.getName());
        String bearerToken = request.getHeader("Authorization");
        System.out.println("bearerToken"+bearerToken);
        
        String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		System.out.println(authorizationToken);
		
		return "admin";
	}

}
