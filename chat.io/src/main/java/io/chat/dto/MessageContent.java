package io.chat.dto;

public class MessageContent {
    Long senderId;
    String content;
    Long conversationId;

    public MessageContent(Long senderId, String content, Long conversationId) {
        this.senderId = senderId;
        this.content = content;
        this.conversationId = conversationId;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
