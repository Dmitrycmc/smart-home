package io.github.dmitrycmc.service;

import io.github.dmitrycmc.dao.DeviceDao;
import io.github.dmitrycmc.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
    public List<Device> findAll() {
        return deviceDao.findAll();
    }

    @Override
    public Page<Device> search(
            Optional<String> nameFilter,
            Optional<Integer> page,
            Optional<Integer> size
    ) {
        return deviceDao.findAll(
                Specification.where(nameFilter.<Specification<Device>>map(s -> (root, query, cb) -> cb.like(root.get("name"), "%" + s + "%")).orElse(null)),
                PageRequest.of(
                        (page.orElse(0)),
                        size.orElse(20)
                )
        );
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

    @Override
    public void setActive(Long id, boolean active) {
        Device device =  deviceDao.findById(id).map(d -> {
            d.setActive(active);
            return d;
        }).orElseThrow(() -> new RuntimeException("Device with id = " + id + " not found"));
        deviceDao.save(device);
    }
}
