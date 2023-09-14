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
		Iterable<Conversation> conversations = convService.getConversations();
		Set<Contact> contacts = new HashSet<>();
		for(Conversation conv : conversations) {
			// check if user exists in either sender or receiver attribute/column
			if(conv.getSender().getId()==userId) {
				// if Sender then add to contacts list
				User user = userService.getUser(conv.getReceiverId()).get();
				Contact contact = new Contact(user.getName(), user.getId(), conv.getId(), user.getImage());
				contacts.add(contact);
			}else if(conv.getReceiverId()==userId) {
				// if Receiver then add to contacts list
				User user = userService.getUser(conv.getSender().getId()).get();
				Contact contact = new Contact(user.getName(), user.getId(), conv.getId(), user.getImage());
				contacts.add(contact);
			}
		}

		User user = userService.getUser(userId).get();
		model.addAttribute("user", user);
		model.addAttribute("contacts", contacts);
		// show that list of contacts in the conversations.html page
		return "conversations";
	}
}
