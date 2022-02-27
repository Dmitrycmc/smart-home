package io.github.dmitrycmc.dao;

import io.github.dmitrycmc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
