package com.example.demo.dto;

import java.util.Date;

public interface IMessageToDTO {
    public MessageDTO messageToDTO(Long id, Long senderId, Long receiverId, Long conversationId, String content, Date date);
}
