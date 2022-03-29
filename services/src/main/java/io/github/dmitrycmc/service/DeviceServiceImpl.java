package io.github.dmitrycmc.service;

import io.github.dmitrycmc.dao.DeviceDao;
import io.github.dmitrycmc.model.Device;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService{

    private final DeviceDao deviceDao;
    private final RedissonClient redissonClient;

    @Autowired
    public DeviceServiceImpl(DeviceDao deviceDao, RedissonClient redissonClient) {
        this.deviceDao = deviceDao;
        this.redissonClient = redissonClient;
    }

    @Override
    public List<Device> findAll() {
        return deviceDao.findAll().stream().map(d -> {
            Boolean active = (Boolean) redissonClient.getMap("deviceIdToActive").get(d.getId());
            d.setActive(active != null && active);
            return d;
        }).collect(Collectors.toList());
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
        redissonClient.getMap("deviceIdToActive").put(id, active);
    }
}
