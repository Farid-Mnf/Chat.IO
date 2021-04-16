package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class SignupController {

	@Autowired
	UserService userService;

	@RequestMapping("/sign-up")
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		return "sign-up";
	}

	@RequestMapping("/new-user")
	public String finishSign(@ModelAttribute User user, Model model) {
		// save user to h2 database
		userService.saveOrUpdate(user);

		model.addAttribute("user", user);
		model.addAttribute("allSystemContacts", userService.getAllContacts(user.getId()));
		// display users in home
		return "contacts";
	}
}
