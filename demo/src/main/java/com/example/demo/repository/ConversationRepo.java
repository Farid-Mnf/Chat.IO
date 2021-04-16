package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Conversation;

public interface ConversationRepo extends CrudRepository<Conversation, Long> {

}
