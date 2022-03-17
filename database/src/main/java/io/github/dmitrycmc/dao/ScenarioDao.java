package io.github.dmitrycmc.dao;

import io.github.dmitrycmc.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioDao extends JpaRepository<Scenario, Long> {
}
