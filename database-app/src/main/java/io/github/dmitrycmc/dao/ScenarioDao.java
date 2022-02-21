package io.github.dmitrycmc.dao;

import io.github.dmitrycmc.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioDao extends JpaRepository<Scenario, Long> {
}
