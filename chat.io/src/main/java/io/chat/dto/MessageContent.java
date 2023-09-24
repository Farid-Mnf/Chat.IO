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
    public Long getSenderId() {
        return senderId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
