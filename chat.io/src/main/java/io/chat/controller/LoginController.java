package io.chat.controller;

import io.chat.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.chat.entity.User;
import io.chat.service.UserService;

import java.util.Set;

@Controller
public class LoginController {
	UserService userService;
	@Autowired
	public LoginController(UserService userService){
		this.userService = userService;
	}

	@GetMapping("/showLogin")
	public String showLogin(Model model) {
		// store User object in model to be used by the login form
		model.addAttribute("user", new User());
		// show login.html page
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model) {
		Iterable<User> users = userService.getAllUsers();
		// loop through all users
		for (User tempUser : users) {
			if (tempUser.getEmail().equals(user.getEmail())) {
				if (tempUser.getPassword().equals(user.getPassword())) {
					// found user record in h2 database and is now authorized to log in
					user = userService.getUser(tempUser.getId()).get();
					model.addAttribute("user", user);
					Set<UserDTO> contacts = userService.getAllContacts(user.getId());
					model.addAttribute("contacts", contacts);
					return "contact-finder";
				}
			}
		}
		// return login page if user doesn't exist
		return "login";
	}
}
