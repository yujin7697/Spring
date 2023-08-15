package com.test.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SimpleController {
	
	@GetMapping("/user")
	public void user() {
		log.info("GET /user...");
	}
	
	@GetMapping("/member")
	public void member() {
		log.info("GET /member...");
	}
	
	@GetMapping("/admin")
	public void admin() {
		log.info("GET /admin...");
	}

}