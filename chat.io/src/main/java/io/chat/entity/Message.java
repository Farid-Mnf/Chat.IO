package io.chat.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Message {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	long id;
	String content;
	Date date;
	Boolean seen;
	Long senderId;
	@ManyToOne
	@JoinColumn(name="conversation_id")
	Conversation conversation;
	public Message() {}
	public Message(String content, Date date, boolean seen, Long senderId) {
		this.content = content;
		this.date = date;
		this.seen = seen;
		this.senderId = senderId;
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

}
