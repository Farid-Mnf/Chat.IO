package io.chat.dto;

import org.springframework.stereotype.Component;

@Component
public class ConnectionInfo {
    private int userId;
    private int contactId;

    public ConnectionInfo(){}

    public int getUserId() {
        return userId;
    }

    public int getContactId() {
        return contactId;
    }

}
