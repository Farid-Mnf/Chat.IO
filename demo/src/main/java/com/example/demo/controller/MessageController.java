package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Conversation;
import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.service.ConversationService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

@Controller
public class MessageController {

	@Autowired
	UserService userService;
	
	@Autowired
	ConversationService convService;
	
	@GetMapping("/messages/{userId}/{contactId}/{convId}")
	public String getMessages(	@PathVariable("userId") long userId,
								@PathVariable("contactId") long contactId,
								@PathVariable("convId") long convId,
								Model model) {
		
		User user = userService.getUser(userId).get();
		User contact = userService.getUser(contactId).get();
		Conversation conv = convService.getConversation(convId).get();
		Set<Message> messages = conv.getMessages();
		List<Message> sortedMessages = new ArrayList<Message>(messages);
		sortedMessages.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
		
		for(Message message : sortedMessages) {
			System.out.println(message.getContent()+" (" + message.getUser().getName() + ")");
		}
		model.addAttribute("user", user);
		model.addAttribute("contact", contact);
		model.addAttribute("messages", sortedMessages);
		model.addAttribute("convId", convId);
		return "chat";
	}

}
