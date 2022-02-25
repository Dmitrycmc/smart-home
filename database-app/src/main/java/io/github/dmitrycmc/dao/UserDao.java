package io.github.dmitrycmc.dao;

import io.github.dmitrycmc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
