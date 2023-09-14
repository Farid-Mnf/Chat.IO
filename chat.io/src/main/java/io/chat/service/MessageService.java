package io.chat.service;

import io.chat.entity.Message;
import io.chat.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    MessageRepo messageRepo;
	@Autowired
	MessageService(MessageRepo messageRepo){
		this.messageRepo = messageRepo;
	}
	public Message save(Message Message) {
		return messageRepo.save(Message);
	}

}
