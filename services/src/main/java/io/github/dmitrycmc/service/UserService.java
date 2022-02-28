package io.github.dmitrycmc.service;

import io.github.dmitrycmc.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> search();

    Optional<User> findById(Long id);

    void deleteById(Long id);

    void save(User user);
}
