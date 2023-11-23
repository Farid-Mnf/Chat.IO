package io.chat.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import io.chat.dto.UserDTO;
import io.chat.entity.Contact;
import io.chat.entity.Conversation;
import io.chat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.chat.entity.User;

@Service
public class UserService {
    UserRepo userRepo;

	ConversationService convService;
	@Autowired
	public UserService(UserRepo userRepo, ConversationService convService){
		this.userRepo = userRepo;
		this.convService = convService;
	}
	public Set<UserDTO> getAllContacts(long userId){
		Iterable<User> users = userRepo.findAll();
		Set<UserDTO> contacts = new HashSet<>();
		for(User user : users) {
			if(user.getId()!=userId) {
				UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getImage());
				contacts.add(userDTO);
			}
		}
		return contacts;
	}

	public Set<UserDTO> getSuggestedContacts(long userId){
		// TODO: suggested contacts
		Set<Contact> myContacts = getContacts(userId);
		Set<UserDTO> suggestedContacts = new HashSet<>();
		Set<UserDTO> allContacts = getAllContacts(userId);

		HashMap<Long, Contact> contactsMap = new HashMap<>();
		for (Contact contact:
			 myContacts) {
			contactsMap.put(contact.getId(), contact);
		}

		for(UserDTO userDTO : allContacts){
			if(contactsMap.get(userDTO.getId()) == null){
				suggestedContacts.add(userDTO);
			}
		}

		return suggestedContacts;
	}

	public Set<Contact> getContacts(long userId) {

		Iterable<Conversation> conversations = convService.getConversations();
		Set<Contact> contacts = new HashSet<>();
		for(Conversation conv : conversations) {
			// check if user exists in either sender or receiver attribute/column
			if(conv.getSender().getId()==userId) {
				// if Sender then add to contacts list
				User user = getUser(conv.getReceiverId()).get();
				Contact contact = new Contact(user.getName(), user.getId(), conv.getId(), user.getImage());
				contacts.add(contact);
			}else if(conv.getReceiverId()==userId) {
				// if Receiver then add to contacts list
				User user = getUser(conv.getSender().getId()).get();
				Contact contact = new Contact(user.getName(), user.getId(), conv.getId(), user.getImage());
				contacts.add(contact);
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
	public Optional<User> getUserByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}
	public User saveOrUpdate(User user) {
		return userRepo.save(user);
	}
}
