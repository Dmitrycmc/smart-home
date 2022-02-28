package io.github.dmitrycmc.service;

import io.github.dmitrycmc.dao.DeviceDao;
import io.github.dmitrycmc.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void save(Device device) {
        deviceDao.save(device);
    }
}
