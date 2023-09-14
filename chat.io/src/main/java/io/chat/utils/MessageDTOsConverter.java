package io.chat.utils;

import io.chat.dto.MessageDTO;
import io.chat.entity.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageDTOsConverter {
    public static List<MessageDTO> convertMessagesToMessagesDTOs(List<Message> messages){
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message:
                messages) {
            MessageDTO messageDTO = new MessageDTO(message.getId(),
                    message.getSenderId(),
                    message.getConversation().getId(),
                    message.getContent(),
                    message.getDate()
            );

            messageDTOS.add(messageDTO);

        }
        return messageDTOS;
    }
}
