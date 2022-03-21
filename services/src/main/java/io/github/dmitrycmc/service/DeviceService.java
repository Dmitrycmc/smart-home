package io.github.dmitrycmc.service;

import io.github.dmitrycmc.model.Device;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    List<Device> findAll();

    Page<Device> search(
            Optional<String> nameFilter,
            Optional<Integer> page,
            Optional<Integer> size
    );

    Optional<Device> findById(Long id);

    void deleteById(Long id);

    void save(Device device);

    void toggle(Long id);
}
