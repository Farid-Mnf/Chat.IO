package io.chat.repository;

import org.springframework.data.repository.CrudRepository;

import io.chat.entity.Message;

public interface MessageRepo extends CrudRepository<Message, Long> {

}
