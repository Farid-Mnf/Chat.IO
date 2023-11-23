package io.chat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.chat.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    public Optional<User> findUserByEmail(String email);
}
