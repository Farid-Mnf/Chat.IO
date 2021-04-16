package com.example.demo.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/messages/{id}")
	public String getUserMessages(@PathVariable(value="id") String id , Model model) {
		
		Optional<User> tempUser = userService.getUser(Integer.parseInt(id));
		
		User user = null;
		Set<Message> messages = null;
		
		if(tempUser.isPresent()) {
			user = tempUser.get();
//			messages = user.getMessages();
			model.addAttribute("user", user);
			model.addAttribute("messages", messages);
		}

		return "messages";
	}
}
