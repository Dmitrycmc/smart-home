package io.github.dmitrycmc.service;

import io.github.dmitrycmc.model.Device;

import java.util.List;

public interface DeviceService {
    public List<Device> search();

    public void save(Device device);
}
