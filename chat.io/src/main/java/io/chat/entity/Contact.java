package io.chat.entity;

public class Contact {

	String name;
	long id;
	long convId;
	
	public Contact() {}
	
	public Contact(String name, long id, long convId) {
		this.name = name;
		this.id = id;
		this.convId = convId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getConvId() {
		return convId;
	}

	public void setConvId(long convId) {
		this.convId = convId;
	}
	
}
