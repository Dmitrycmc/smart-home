package io.github.dmitrycmc.service;

import io.github.dmitrycmc.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> search();

    Optional<Role> findById(Long id);

    List<Role> findAllByNameIsIn(List<String> names);

    void deleteById(Long id);

    void save(Role role);
}
