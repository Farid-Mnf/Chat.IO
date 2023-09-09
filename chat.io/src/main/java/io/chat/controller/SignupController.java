package io.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import io.chat.entity.User;
import io.chat.service.UserService;

@Controller
public class SignupController {
	UserService userService;

	@Autowired
	public SignupController(UserService userService){
		this.userService = userService;
	}
	
	@RequestMapping("/sign-up")
	public String signUp(Model model) {
		// populate sign-up form with new User object
		model.addAttribute("user", new User());
		return "sign-up";
	}

	@RequestMapping("/new-user")
	public String finishSign(@ModelAttribute User user, Model model) {
		// save user to h2 database
		userService.saveOrUpdate(user);
		// return that user object
		model.addAttribute("user", user);
		// return list of suggested contacts
		model.addAttribute("contacts", userService.getAllContacts(user.getId()));
		// search for contact
		return "contact-finder";
	}
}
