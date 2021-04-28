package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Conversation;
import com.example.demo.entity.Message;
import com.example.demo.entity.MessageModel;
import com.example.demo.entity.User;
import com.example.demo.service.ConversationService;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

@RestController
public class MessageRestController {

	@Autowired
	UserService userService;
	
	@Autowired
	ConversationService convService;
	
	@Autowired
	MessageService messageService;
	
	@PostMapping("/message") // handle Asynchronous POST request XMLHttpRequest
	public void saveMessage(@RequestBody MessageModel messageModel) {
		// get user and conversation that message belongs to
		User user = userService.getUser(messageModel.getUserId()).get();
		Conversation conv = convService.getConversation(messageModel.getConvId()).get();
		
		// create Message object and store it in h2 database
		Message message = new Message();
		message.setContent(messageModel.getContent());
		message.setDate(new Date());
		message.setUser(user);
		message.setConversation(conv);
		// just save the message, no need for return value right now
		messageService.save(message);
	}
}
