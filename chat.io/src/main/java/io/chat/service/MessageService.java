package io.chat.service;

import java.util.Optional;

import io.chat.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.chat.entity.Message;

@Service
public class MessageService {
	
	@Autowired
    MessageRepo messageRepo;

	
	public Iterable<Message> getAllMessages(){
		return messageRepo.findAll();
	}
	
	public Optional<Message> getMessage(long id) {
		return messageRepo.findById(id);
	}
	
	public void deleteMessage(Message Message) {
		messageRepo.delete(Message);
	}
	
	public void deleteMessageById(long id) {
		messageRepo.deleteById(id);
	}
	
	public Message save(Message Message) {
		return messageRepo.save(Message);
	}

}
