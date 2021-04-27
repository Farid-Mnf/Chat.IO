package com.example.demo.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Contact;
import com.example.demo.entity.Conversation;
import com.example.demo.entity.User;
import com.example.demo.service.ConversationService;
import com.example.demo.service.UserService;

@Controller
public class ConversationController {

	@Autowired
	ConversationService convService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/connect/{senderId}/{recieverId}")
	public String makeConversation(@PathVariable(value="senderId") long senderId,
			@PathVariable(value="recieverId") long recieverId, Model model) {
		User user = userService.getUser(senderId).get();
		Conversation conv = new Conversation(user,recieverId);
		convService.save(conv);
		User contact = userService.getUser(recieverId).get();
		model.addAttribute("convId", conv.getId());
		model.addAttribute("user", user);
		model.addAttribute("contact", contact);
		return "redirect:/conversations/" + user.getId();
	}
	
	@GetMapping("/conversations/{userId}")
	public String getUserConversations(@PathVariable("userId") long userId, Model model) {
		Iterable<Conversation> convs = convService.getConversations();
		Set<Contact> contacts = new HashSet<>();
		for(Conversation conv : convs) {
			if(conv.getSender().getId()==userId) {
				User user = userService.getUser(conv.getRecieverId()).get();
				Contact contact = new Contact(user.getName(), user.getId(), conv.getId());
				contacts.add(contact);
			}else if(conv.getRecieverId()==userId) {
				User user = userService.getUser(conv.getSender().getId()).get();
				Contact contact = new Contact(user.getName(), user.getId(), conv.getId());
				contacts.add(contact);
			}
		}
		
		User user = userService.getUser(userId).get();
		model.addAttribute("user", user);
		model.addAttribute("contacts", contacts);
		return "conversations";
	}
	
}
