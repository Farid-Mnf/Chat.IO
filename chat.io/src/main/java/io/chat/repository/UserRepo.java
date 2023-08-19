package io.chat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.chat.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

}
