package io.chat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.chat.dto.IMessageToDTO;
import io.chat.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.chat.entity.Conversation;
import io.chat.entity.Message;
import io.chat.service.ConversationService;
import io.chat.service.MessageService;
import io.chat.service.UserService;

@RestController
public class MessageRestController {
	IMessageToDTO iMessageToDTO;
	UserService userService;
	ConversationService convService;
	MessageService messageService;

	@Autowired
	public MessageRestController(IMessageToDTO iMessageToDTO, UserService userService, ConversationService convService, MessageService messageService) {
		this.iMessageToDTO = iMessageToDTO;
		this.userService = userService;
		this.convService = convService;
		this.messageService = messageService;
	}

	@PostMapping("/message") // handle Asynchronous POST request XMLHttpRequest
	public ResponseEntity saveMessage(@RequestBody MessageDTO messageDTO) {
		// get user and conversation that message belongs to
		Conversation conv = convService.getConversation(messageDTO.getConversationId()).get();
		
		// create Message object and store it in h2 database
		Message message = new Message();

		message.setSenderId(messageDTO.getSenderId());
		message.setContent(messageDTO.getContent());
		message.setDate(new Date());
		message.setSeen(false);
		message.setConversation(conv);

		messageService.save(message);
		return new ResponseEntity(HttpStatus.CREATED);
	}


	@GetMapping("/chatMessages/{userId}/{receiverId}/{convId}")
	public List<MessageDTO> haveNewMessage(@PathVariable("userId") long userId,
								 @PathVariable("receiverId") long receiverId,
								 @PathVariable("convId") long convId){
		Conversation conversation = convService.getConversation(convId).get();
		List<Message> messages = conversation.getMessages();
		List<MessageDTO> dtoMessages = new ArrayList<>();
		for (Message message:
			 messages) {
			MessageDTO messageDTO = iMessageToDTO
					.messageToDTO(message.getId(), message.getSenderId(), message.getConversation().getId(), message.getContent(), message.getDate());

			dtoMessages.add(messageDTO);
		}

		dtoMessages.sort((o2, o1) -> o1.getDate().compareTo(o2.getDate()));

		return dtoMessages;
	}
}
