package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
}
