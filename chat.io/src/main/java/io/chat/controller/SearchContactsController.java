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

        Iterable<UserDTO> users = userService.getAllContacts(contactInfo.getUserId());

        Set<UserDTO> contacts = new HashSet<>();
        for(UserDTO user : users) {
            if(user.getName().toLowerCase().contains(contactInfo.getContactName().toLowerCase())) {
                contacts.add(user);
            }
        }

        return contacts;
    }
}
