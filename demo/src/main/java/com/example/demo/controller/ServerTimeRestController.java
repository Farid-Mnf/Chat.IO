package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerTimeRestController {
	
	@GetMapping("/clock")
	public String getClock() {
		return new java.util.Date().toString();
	}

}