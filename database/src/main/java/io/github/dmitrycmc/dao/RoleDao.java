package io.github.dmitrycmc.dao;

import io.github.dmitrycmc.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Long> {
    List<Role> findAllByNameIsIn(List<String> names);
}
