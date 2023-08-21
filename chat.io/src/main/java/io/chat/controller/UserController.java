package io.chat.controller;

import java.util.HashSet;
import java.util.Set;

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
	@Autowired
	UserService userService;
	
	@RequestMapping("/findContact/{id}") // loop through all users and compare their name with input value
	public String findContact(@PathVariable("id") long id, @RequestParam("contactName") String contactName, Model model) {

		Iterable<User> users = userService.getAllContacts(id);

		Set<User> contacts = new HashSet<>();
		for(User user : users) {
			if(user.getName().toLowerCase().contains(contactName.toLowerCase())) {
				contacts.add(user);
			}
		}

		int count = contacts.size();

		if(count == 0)
			model.addAttribute("searchResult", "empty");
		else
			model.addAttribute("contacts", contacts);
		model.addAttribute("user", userService.getUser(id).get());
		return "contact-result";
	}
	
	@RequestMapping("/search-contact/{id}")
	public String searchContact(@PathVariable("id") long id, Model model) {
		User user = userService.getUser(id).get();
		model.addAttribute("user", user);
		return "contact-finder";
	}
}
