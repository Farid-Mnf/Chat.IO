package io.chat.dto;

import java.util.Date;

public class MessageDTO {

    Long id;
    Long senderId;

    Long conversationId;
    String content;
    Date date;

    public MessageDTO(Long id, Long senderId, Long conversationId, String content, Date date) {
        this.senderId = senderId;
        this.conversationId = conversationId;
        this.content = content;
        this.date = date;
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
