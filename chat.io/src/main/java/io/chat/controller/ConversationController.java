package io.chat.controller;

import io.chat.entity.Contact;
import io.chat.entity.Conversation;
import io.chat.entity.User;
import io.chat.service.ConversationService;
import io.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.Set;

@Controller
public class ConversationController {
	ConversationService convService;
	UserService userService;

	@Autowired
	public ConversationController(ConversationService convService, UserService userService) {
		this.convService = convService;
		this.userService = userService;
	}

	@GetMapping("/conversations/{userId}")
	public String getUserConversations(@PathVariable("userId") long userId, Model model) {
		// get list of conversations

		Set<Contact> contacts = userService.getContacts(userId);

		User user = userService.getUser(userId).get();
		model.addAttribute("user", user);
		model.addAttribute("contacts", contacts);
		// show that list of contacts in the conversations.html page
		return "conversations";
	}
}
