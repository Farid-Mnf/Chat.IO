package io.chat.controller;

import io.chat.dto.MessageContent;
import io.chat.entity.Conversation;
import io.chat.entity.Message;
import io.chat.service.ConversationService;
import io.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatMessageController {

    MessageService messageService;
    ConversationService conversationService;
    @Autowired
    public ChatMessageController(MessageService messageService, ConversationService conversationService){
        this.messageService = messageService;
        this.conversationService = conversationService;
    }
    
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public MessageContent handleMessage(MessageContent messageContent) throws InterruptedException {
        Thread.sleep(1000);
        Conversation conversation = conversationService.getConversation(messageContent.getConversationId()).get();
        Message message = new Message();
        message.setContent(messageContent.getContent());
        message.setDate(new Date());
        message.setConversation(conversation);
        message.setSenderId(messageContent.getSenderId());

        messageService.save(message);
        return new MessageContent(messageContent.getSenderId(), messageContent.getContent(), messageContent.getConversationId());
    }
}