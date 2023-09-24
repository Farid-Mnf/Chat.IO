package io.chat.controller;

import io.chat.dto.UserDTO;
import io.chat.entity.User;
import io.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
public class UserController {
	UserService userService;
	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@RequestMapping("/search-contact/{id}")
	public String searchContact(@PathVariable("id") long id, Model model) {
		System.out.println("===== called /search-contact/id");
		User user = userService.getUser(id).get();
		Set<UserDTO> contacts = userService.getAllContacts(id);
		model.addAttribute("user", user);
		model.addAttribute("contacts", contacts);
		return "contact-finder";
	}
}
