package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import com.example.demo.service.MessageService;

@Controller
public class MessageController {

//	@GetMapping("/messages/{userId}/{convId}")
	MessageService messageService;
	public MessageController() {
//		me
	}
	
}
