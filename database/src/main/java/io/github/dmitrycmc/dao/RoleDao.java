package io.github.dmitrycmc.dao;

import io.github.dmitrycmc.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
