package io.chat.controller;

import io.chat.dto.UserDTO;
import io.chat.entity.User;
import io.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
		// pass User object in model to be used and populated by the login form data
		model.addAttribute("user", new User());
		// redirect to Log in html template
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model) {
		Iterable<User> users = userService.getAllUsers();
		// loop through all users
		for (User userItem : users) {
			if (userItem.getEmail().equals(user.getEmail())) {
				if (userItem.getPassword().equals(user.getPassword())) {
					// found user record in h2 database and is now authorized to log in
					user = userService.getUser(userItem.getId()).get();
					// save that user in the model
					model.addAttribute("user", user);
					// get list of contacts as UserDTOs
					Set<UserDTO> contacts = userService.getAllContacts(user.getId());
					// pass that list to the model
					model.addAttribute("contacts", contacts);
					// show contact finder and suggested contacts template
					return "contact-finder";
				}
			}
		}
		// return login page if user doesn't exist
		return "login";
	}
}
