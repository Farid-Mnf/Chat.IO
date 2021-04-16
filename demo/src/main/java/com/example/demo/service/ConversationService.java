package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Conversation;
import com.example.demo.repository.ConversationRepo;

@Service
public class ConversationService {
	
	@Autowired
	ConversationRepo convRepo;

	public Iterable<Conversation> getConversations(){
		return convRepo.findAll();
	}
	
	public Optional<Conversation> getConversation(long id) {
		return convRepo.findById(id);
	}
	
	public void deleteConversation(Conversation Conversation) {
		convRepo.delete(Conversation);
	}
	
	public void deleteConversationById(long id) {
		convRepo.deleteById(id);
	}
	
	public boolean doConversationExist(long id) {
		return convRepo.existsById(id);
	}
	
	public Conversation save(Conversation Conversation) {
		return convRepo.save(Conversation);
	}

}
