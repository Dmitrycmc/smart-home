package io.github.dmitrycmc.dao;

import io.github.dmitrycmc.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceDao extends JpaRepository<Device, Long> {
    @Override
    Optional<Device> findById(Long id);

    @Override
    void deleteById(Long aLong);
}
