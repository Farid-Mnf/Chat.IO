package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="sender_id")
	User sender;

	long recieverId;

	@OneToMany(mappedBy="conversation")
	Set<Message> messages = new HashSet<>();
	
	public Conversation() {	}

	public Conversation(User sender, long recieverId) {
		this.sender = sender;
		this.recieverId = recieverId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(long recieverId) {
		this.recieverId = recieverId;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

}
