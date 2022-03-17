package io.github.dmitrycmc.service;

import io.github.dmitrycmc.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    List<Device> search();

    Optional<Device> findById(Long id);

    void deleteById(Long id);

    void save(Device device);
}
