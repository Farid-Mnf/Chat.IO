package io.chat.entity;
public class Contact {
	String name;
	long id;
	long convId;
	String image;
	public Contact() {}
	public Contact(String name, long id, long convId, String image) {
		this.name = name;
		this.id = id;
		this.convId = convId;
		this.image = image;
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
	public long getConvId() { return convId; }
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
