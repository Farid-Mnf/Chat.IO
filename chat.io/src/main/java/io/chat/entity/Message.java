package io.chat.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String content;
	Date date;
	Boolean seen;

	Long senderId;

	Long receiverId;

	@ManyToOne
	@JoinColumn(name="conversation_id")
	Conversation conversation;

	
	public Message() {}

	public Message(String content, Date date, boolean seen, Long senderId, Long receiverId) {
		this.content = content;
		this.date = date;
		this.seen = seen;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}
	
	

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getSeen() {
		return seen;
	}

	public void setSeen(Boolean seen) {
		this.seen = seen;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
}
