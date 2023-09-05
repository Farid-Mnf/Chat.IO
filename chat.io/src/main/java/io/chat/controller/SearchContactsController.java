package io.chat.controller;

import io.chat.dto.UserDTO;
import io.chat.entity.User;
import io.chat.service.UserService;
import io.chat.utils.ContactInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class SearchContactsController {
    UserService userService;
    @Autowired
    public SearchContactsController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/get-contacts")
    public Set<UserDTO> getContactsList(@RequestBody ContactInfo contactInfo){
        System.out.println("Id: " + contactInfo.getUserId());
        System.out.println("Name : " + contactInfo.getContactName());


        Iterable<User> users = userService.getAllContacts(contactInfo.getUserId());

        Set<UserDTO> contacts = new HashSet<>();
        for(User user : users) {
            if(user.getName().toLowerCase().contains(contactInfo.getContactName().toLowerCase())) {
                UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getName());
                contacts.add(userDTO);
            }
        }

        return contacts;
    }
}
