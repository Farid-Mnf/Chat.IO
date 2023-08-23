package io.chat.dto;

public class MessageContent {
    Long senderId;
    String content;

    public MessageContent(Long senderId, String content) {
        this.senderId = senderId;
        this.content = content;
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
