package com.example.demo.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String content;
	Date date;
	
	@ManyToOne
	@JoinColumn(name="conversation_id")
	Conversation conversation;
	
	public Message() {}

	public Message(String content, Date date) {
		this.content = content;
		this.date = date;
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
}
