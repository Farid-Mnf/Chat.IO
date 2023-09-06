package io.chat.controller;

import io.chat.dto.ConnectionInfo;
import io.chat.entity.Conversation;
import io.chat.entity.User;
import io.chat.service.ConversationService;
import io.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConnectRestController {

    UserService userService;
    ConversationService conversationService;
    @Autowired
    public ConnectRestController(UserService userService, ConversationService conversationService){
        this.userService = userService;
        this.conversationService = conversationService;
    }

    @PostMapping("/connect")
    public void connect(@RequestBody ConnectionInfo connectionInfo){
        // get the User object of the user who sent the connection request
        User user = userService.getUser(connectionInfo.getUserId()).isPresent()?
                userService.getUser(connectionInfo.getUserId()).get() : null;

        List<Conversation> conversations = new ArrayList<>();

        Iterable<Conversation> iterable = conversationService.getConversations();

        iterable.forEach(conversations::add);

        for (Conversation tempConv:
                conversations) {
            // check if already connected according to POV of both sides
            if(tempConv.getSender().getId() == connectionInfo.getUserId() &&
                    tempConv.getReceiverId() == connectionInfo.getContactId())
            {
                System.out.println("Found one conversation----------------");
                return;
            }else if(tempConv.getSender().getId() == connectionInfo.getContactId() &&
                    tempConv.getReceiverId() == connectionInfo.getUserId()){
                System.out.println("Found one conversation----------------");
                return;
            }
        }

        // create Conversation between 2 users
        Conversation conv = new Conversation(user,connectionInfo.getContactId());

        // finally save that Conversation
        conversationService.save(conv);

        // redirect the request to conversations controller with the user id
//        return "redirect:/conversations/" + user.getId();
    }
}
