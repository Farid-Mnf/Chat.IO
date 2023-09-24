package io.chat.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.chat.entity.Conversation;
import io.chat.repository.ConversationRepo;

@Service
public class ConversationService {
	ConversationRepo convRepo;
	@Autowired
	public ConversationService(ConversationRepo conversationRepo){
		this.convRepo = conversationRepo;
	}
	public Iterable<Conversation> getConversations(){
		return convRepo.findAll();
	}
	public Optional<Conversation> getConversation(long id) {
		return convRepo.findById(id);
	}
	public Conversation save(Conversation Conversation) {
		return convRepo.save(Conversation);
	}
}
