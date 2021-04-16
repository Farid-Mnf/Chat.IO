package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Message;

public interface MessageRepo extends CrudRepository<Message, Long> {

}
