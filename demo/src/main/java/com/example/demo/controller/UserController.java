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
		System.out.println("=======\nSearch for: " + contactName);
		Iterable<User> users = userService.getAllContacts(id);
		Set<User> contacts = new HashSet<>();
		for(User user : users) {
			if(user.getName().contains(contactName)) {
				System.out.println("found contact with name: " + user.getName());
				contacts.add(user);
			}
		}
		model.addAttribute("contacts", contacts);
		model.addAttribute("user", userService.getUser(id).get());
		return "contact-result";
	}

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
		// display users in home
		return "contact-finder";
	}

	@GetMapping("/showLogin")
	public String showLogin(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model) {
		Iterable<User> users = userService.getAllUsers();
		for(User tempUser : users) {
			if(tempUser.getEmail().equals(user.getEmail())) {
				if(tempUser.getPassword().equals(user.getPassword())) {
					user = userService.getUser(tempUser.getId()).get();
					model.addAttribute("user", user);
					return "contact-finder";
				}
			}
		}
		System.out.println("user not found");
		return "login";  // need not to view already added contacts
	}
}
