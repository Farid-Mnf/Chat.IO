package com.example.demo.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.dto.MessageDTO;
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

	UserService userService;

	ConversationService convService;

	@Autowired
	public MessageController(UserService userService, ConversationService conversationService){
		this.userService = userService;
		this.convService = conversationService;
	}

	// load messages in specific conversations between two users
	@GetMapping("/messages/{userId}/{contactId}/{convId}")
	public String getMessages(	@PathVariable("userId") long userId,
								@PathVariable("contactId") long contactId,
								@PathVariable("convId") long convId,
								Model model) {
		User user = userService.getUser(userId).get();
		User contact = userService.getUser(contactId).get();
		Conversation conv = convService.getConversation(convId).get();
		List<Message> messages = conv.getMessages();
		List<MessageDTO> messageDTOS = convertMessagesToMessagesDTOs(messages);
		// sort messages by date
		messageDTOS.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
		// store user, contact, messages, convId in model to be used in chat.html
		model.addAttribute("user", user);
		model.addAttribute("receiverId", contactId);
		model.addAttribute("contact", contact);
		model.addAttribute("messages", messageDTOS);
		model.addAttribute("convId", convId);
		return "chat";
	}

	private List<MessageDTO> convertMessagesToMessagesDTOs(List<Message> messages){
		List<MessageDTO> messageDTOS = new ArrayList<>();
		for (Message message:
			 messages) {
			MessageDTO messageDTO = new MessageDTO(message.getId(),
					message.getSenderId(),
					message.getReceiverId(),
					message.getConversation().getId(),
					message.getContent(),
					message.getDate()
			);

			messageDTOS.add(messageDTO);
		}
		return messageDTOS;
	}
}