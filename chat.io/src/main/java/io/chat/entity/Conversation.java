package io.chat.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Conversation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="sender_id")
	User sender;

	long receiverId;

	@OneToMany(mappedBy="conversation")
	List<Message> messages = new ArrayList<>();
	
	public Conversation() {	}

	public Conversation(User sender, long receiverId) {
		this.sender = sender;
		this.receiverId = receiverId;
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
	public long getReceiverId() {
		return receiverId;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
