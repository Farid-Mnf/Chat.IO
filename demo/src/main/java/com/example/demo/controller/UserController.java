package com.example.demo.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.service.ConversationService;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/findContact/{id}")
	public String findContact(@PathVariable("id") long id, @RequestParam("contactName") String contactName, Model model) {
		// search all contacts with the input (contact name)
		Iterable<User> users = userService.getAllContacts(id);
		Set<User> contacts = new HashSet<>();
		for(User user : users) {
			// for every contact, check if his name matches search input field 
			if(user.getName().contains(contactName)) {
				// if contact name similar to input value add that contact to list
				contacts.add(user);
			}
		}
		
		// store contacts and user in model for HTML rendering
		model.addAttribute("contacts", contacts);
		model.addAttribute("user", userService.getUser(id).get());
		// view the contacts result in contact-result.html page
		return "contact-result";
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
		model.addAttribute("user", user);
		// search for contact
		return "contact-finder";
	}
	
	@RequestMapping("/search-contact/{id}")
	public String searchContact(@PathVariable("id") long id, Model model) {
		User user = userService.getUser(id).get();
		model.addAttribute("user", user);
		return "contact-finder";
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
		for(User tempUser : users) {
			if(tempUser.getEmail().equals(user.getEmail())) {
				if(tempUser.getPassword().equals(user.getPassword())) {
					// found user record in h2 database and is now authorized to login
					user = userService.getUser(tempUser.getId()).get();
					model.addAttribute("user", user);
					return "contact-finder";
				}
			}
		}
		// return login page if user doesn't exist
		return "login";
	}
}
