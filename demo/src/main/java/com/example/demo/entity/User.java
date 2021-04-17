package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column(unique = true)
	String email;
	String password;
	String name;
	

	@OneToMany(mappedBy="sender")
	Set<Conversation> conversations = new HashSet<>();
	
	@OneToMany(mappedBy="user")
	Set<Message> messages = new HashSet<>();
	
	public User() {}
	
	public User(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
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

}
