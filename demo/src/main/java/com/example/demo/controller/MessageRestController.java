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
	
	@PostMapping("/message")
	public void saveMessage(@RequestBody MessageModel messageModel) {
		
		User user = userService.getUser(messageModel.getUserId()).get();
		Conversation conv = convService.getConversation(messageModel.getConvId()).get();
		
		Message message = new Message();
		message.setContent(messageModel.getContent());
		message.setDate(new Date());
		message.setUser(user);
		message.setConversation(conv);

		messageService.save(message);
	}
}
