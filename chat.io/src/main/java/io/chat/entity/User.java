package io.chat.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	Long id;
	@Column(unique = true)
	String email;
	String password;
	String name;
	String image;
	@OneToMany(mappedBy="sender")
	Set<Conversation> conversations = new HashSet<>();
	public User() {}
	public User(String email, String password, String name, String image) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.image = image;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() { return image; }
	public void setImage(String image) { this.image = image; }
}
