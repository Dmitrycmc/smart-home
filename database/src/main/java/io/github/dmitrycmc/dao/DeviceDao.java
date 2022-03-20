package io.github.dmitrycmc.dao;

import io.github.dmitrycmc.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceDao extends JpaRepository<Device, Long>, JpaSpecificationExecutor<Device> {
}
