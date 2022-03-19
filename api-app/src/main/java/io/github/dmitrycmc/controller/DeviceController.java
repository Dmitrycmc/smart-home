package io.github.dmitrycmc.controller;

import io.github.dmitrycmc.dto.DeviceDto;
import io.github.dmitrycmc.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/device")
public class DeviceController {

    DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("")
    public List<DeviceDto> getDevices() {
        return deviceService.search().stream().map(d -> {
            DeviceDto device = new DeviceDto();
            device.setId(d.getId());
            device.setName(d.getName());
            device.setPictures(d.getPictures().stream().map(p -> "/api/picture/" + p.getId()).collect(Collectors.toList()));
            return device;
        }).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public DeviceDto getDevice(@PathVariable Long id) {
        return deviceService.findById(id).map(d -> {
            DeviceDto device = new DeviceDto();
            device.setId(d.getId());
            device.setName(d.getName());
            device.setPictures(d.getPictures().stream().map(p -> "/api/picture/" + p.getId()).collect(Collectors.toList()));
            return device;
        }).orElseThrow(() -> new RuntimeException("Not Fount"));
    }
}
