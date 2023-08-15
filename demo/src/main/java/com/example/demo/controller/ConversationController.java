package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Contact;
import com.example.demo.entity.Conversation;
import com.example.demo.entity.User;
import com.example.demo.service.ConversationService;
import com.example.demo.service.UserService;

@Controller
public class ConversationController {

	@Autowired
	ConversationService convService;

	@Autowired
	UserService userService;

	// connect two users with the id of the two user connected
	@GetMapping("/connect/{senderId}/{recieverId}")
	public String makeConversation(@PathVariable(value="senderId") long senderId,
			@PathVariable(value="recieverId") long recieverId, Model model) {

		// get the User object of the user who sent the connection request
		User user = userService.getUser(senderId).get();

		List<Conversation> convs = new ArrayList<>();

		Iterable<Conversation> iterable = convService.getConversations();

		iterable.forEach(convs::add);

		for (Conversation tempConv:
				convs) {
			if(tempConv.getSender().getId() == senderId && tempConv.getReceiverId() == recieverId){
				System.out.println("Found one conversation----------------");
				return "redirect:/conversations/" + user.getId();
			}else if(tempConv.getSender().getId() == recieverId && tempConv.getReceiverId() == senderId){
				System.out.println("Found one conversation----------------");
				return "redirect:/conversations/" + user.getId();
			}
		}


		// create Conversation between 2 users
		Conversation conv = new Conversation(user,recieverId);

		// finally save that Conversation
		convService.save(conv);

		// redirect the request to conversations controller with the user id
		return "redirect:/conversations/" + user.getId();
	}
	
	@GetMapping("/conversations/{userId}")
	public String getUserConversations(@PathVariable("userId") long userId, Model model) {
		// get list of conversations
		Iterable<Conversation> convs = convService.getConversations();
		Set<Contact> contacts = new HashSet<>();
		for(Conversation conv : convs) {
			// check if user exists in either sender or receiver attribute/column
			if(conv.getSender().getId()==userId) {
				// if Sender then add to contacts list
				User user = userService.getUser(conv.getReceiverId()).get();
				Contact contact = new Contact(user.getName(), user.getId(), conv.getId());
				contacts.add(contact);
			}else if(conv.getReceiverId()==userId) {
				// if Receiver then add to contacts list
				User user = userService.getUser(conv.getSender().getId()).get();
				Contact contact = new Contact(user.getName(), user.getId(), conv.getId());
				contacts.add(contact);
			}
		}
		
		/* get user object that is needed in HTML rendering with Thymeleaf
		 * and store the object in the model alongside contacts list 
		 */
		User user = userService.getUser(userId).get();
		model.addAttribute("user", user);
		model.addAttribute("contacts", contacts);
		// show that list of contacts in the conversations.html page
		return "conversations";
	}
	
}
