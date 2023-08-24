package io.chat.dto;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class IMessageToDTOImpl implements IMessageToDTO {

    @Override
    public MessageDTO messageToDTO(Long id, Long senderId, Long receiverId, Long conversationId, String content, Date date) {
        MessageDTO messageDTO = new MessageDTO(id, senderId, receiverId, conversationId, content, date);
        return messageDTO;
    }
}