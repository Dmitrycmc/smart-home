package io.github.dmitrycmc.dao;

import io.github.dmitrycmc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
