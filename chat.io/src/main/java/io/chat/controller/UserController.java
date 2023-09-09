package io.chat.controller;

import java.util.HashSet;
import java.util.Set;

import io.chat.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.chat.entity.User;
import io.chat.service.UserService;

@Controller
public class UserController {
	UserService userService;
	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	
	@RequestMapping("/search-contact/{id}")
	public String searchContact(@PathVariable("id") long id, Model model) {
		User user = userService.getUser(id).get();
		Set<UserDTO> contacts = userService.getAllContacts(id);
		model.addAttribute("user", user);
		model.addAttribute("contacts", contacts);
		System.out.println();
		return "contact-finder";
	}
}
