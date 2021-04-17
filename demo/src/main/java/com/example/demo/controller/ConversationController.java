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
	
	@GetMapping("/connect/{sender_id}/{reciever_id}")
	public String makeConversation(@PathVariable(value="sender_id") long sender_id,
			@PathVariable(value="reciever_id") long reciever_id, Model model) {
		User user = userService.getUser(sender_id).get();
		Conversation conv = new Conversation(user,reciever_id);
		User reciever = userService.getUser(reciever_id).get();
		convService.save(conv);
		model.addAttribute("user", user);
		model.addAttribute("reciever", reciever);
		return "chat";
	}
	
	@GetMapping("/conversations/{userId}")
	public String getUserConversations(@PathVariable("userId") long userId, Model model) {
		Iterable<Conversation> convs = convService.getConversations();
		Set<Contact> contacts = new HashSet<>();
		for(Conversation conv : convs) {
			if(conv.getSender().getId()==userId) {
				User user = userService.getUser(conv.getReciever_id()).get();
				Contact contact = new Contact(user.getName(), user.getId(), conv.getId());
				contacts.add(contact);
			}else if(conv.getReciever_id()==userId) {
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
