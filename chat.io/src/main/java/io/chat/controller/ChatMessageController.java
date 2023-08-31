package io.chat.controller;

import io.chat.dto.MessageContent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public MessageContent handleMessage(MessageContent messageContent) throws InterruptedException {
        Thread.sleep(1000);
        return new MessageContent(messageContent.getSenderId(), messageContent.getContent(), messageContent.getConversationId());
    }
}