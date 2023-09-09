package io.chat.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import io.chat.dto.UserDTO;
import io.chat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.chat.entity.User;

@Service
public class UserService {
    UserRepo userRepo;
	@Autowired
	public UserService(UserRepo userRepo){
		this.userRepo = userRepo;
	}
	
	public Set<UserDTO> getAllContacts(long userId){
		Iterable<User> users = userRepo.findAll();
		Set<UserDTO> contacts = new HashSet<>();
		for(User user : users) {
			if(user.getId()!=userId) {
				UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getName());
				contacts.add(userDTO);
			}
		}
		return contacts;
	}
	
	public Iterable<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public Optional<User> getUser(long id) {
		return userRepo.findById(id);
	}
	
	public void deleteUser(User user) {
		userRepo.delete(user);
	}
	
	public void deleteUserById(long id) {
		userRepo.deleteById(id);
	}
	
	public boolean doUserExist(long id) {
		return userRepo.existsById(id);
	}
	
	public User saveOrUpdate(User user) {
		return userRepo.save(user);
	}

}
