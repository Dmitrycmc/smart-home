package io.github.dmitrycmc.service;

import io.github.dmitrycmc.dao.DeviceDao;
import io.github.dmitrycmc.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService{

    private final DeviceDao deviceDao;

    @Autowired
    public DeviceServiceImpl(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Override
    public List<Device> search() {
        return deviceDao.findAll();
    }

    @Override
    public Optional<Device> findById(Long id) {
        return deviceDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        deviceDao.deleteById(id);
    }

    @Override
    public void save(Device device) {
        deviceDao.save(device);
    }
}
