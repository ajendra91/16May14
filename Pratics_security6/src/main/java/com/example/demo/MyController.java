package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@RequestMapping("/getData1")
	public String getData1() {
		return "getData1";
	}
	
	@PostMapping("/getData2")
	public String getData2() {
		return "getData2";
	}
	
}
