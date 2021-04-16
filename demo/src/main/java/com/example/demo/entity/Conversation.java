package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
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
	
	@ManyToOne
	@JoinColumn(name="sender_id")
	User sender;

	long reciever_id;

	@OneToMany(mappedBy="conversation")
	Set<Message> messages = new HashSet<>();
	
	public Conversation() {	}

	public Conversation(User sender, long reciever_id) {
		this.sender = sender;
		this.reciever_id = reciever_id;
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

	public long getReciever_id() {
		return reciever_id;
	}

	public void setReciever_id(long reciever_id) {
		this.reciever_id = reciever_id;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
}
