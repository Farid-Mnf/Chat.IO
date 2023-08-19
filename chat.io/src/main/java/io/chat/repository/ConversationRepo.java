package io.chat.repository;

import io.chat.entity.Conversation;
import org.springframework.data.repository.CrudRepository;

public interface ConversationRepo extends CrudRepository<Conversation, Long> {

}
