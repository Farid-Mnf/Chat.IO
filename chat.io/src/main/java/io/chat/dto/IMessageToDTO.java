package io.chat.dto;

import java.util.Date;

public interface IMessageToDTO {
    public MessageDTO messageToDTO(Long id, Long senderId, Long conversationId, String content, Date date);
}
