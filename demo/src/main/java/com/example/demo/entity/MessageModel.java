package com.example.demo.entity;

import java.util.Date;

public class MessageModel {
	String content;
	long convId;
	long userId;
	
	public MessageModel() {}

	public MessageModel(String content, long convId, long userId) {
		this.content = content;
		this.convId = convId;
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getConvId() {
		return convId;
	}

	public void setConvId(long convId) {
		this.convId = convId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
