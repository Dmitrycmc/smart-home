package io.github.dmitrycmc.controller;

import io.github.dmitrycmc.dto.DeviceDto;
import io.github.dmitrycmc.model.Device;
import io.github.dmitrycmc.service.DeviceService;
import io.github.dmitrycmc.websocket.WebSocketHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Device", description = "Service for devices ")
@RestController
@RequestMapping("v1/device")
public class DeviceController {

    private final DeviceService deviceService;
    private final WebSocketHandler webSocketHandler;

    @Autowired
    public DeviceController(DeviceService deviceService, WebSocketHandler webSocketHandler) {
        this.deviceService = deviceService;
        this.webSocketHandler = webSocketHandler;
    }

    @GetMapping("")
    public List<DeviceDto> getDevices() {
        return deviceService.findAll().stream().map(d -> {
            DeviceDto device = new DeviceDto();
            device.setId(d.getId());
            device.setX(d.getX());
            device.setY(d.getY());
            device.setName(d.getName());
            device.setActive(d.isActive());
            device.setPictures(d.getPictures().stream().map(p -> "/api/picture/" + p.getId()).collect(Collectors.toList()));
            return device;
        }).collect(Collectors.toList());
    }

    @GetMapping("search")
    public Page<DeviceDto> searchDevices(@RequestParam Optional<Integer> page,
                                      @RequestParam Optional<Integer> size,
                                      @RequestParam Optional<String> nameFilter) {
        return deviceService.search(nameFilter, page, size).map(d -> {
            DeviceDto device = new DeviceDto();
            device.setId(d.getId());
            device.setX(d.getX());
            device.setY(d.getY());
            device.setName(d.getName());
            device.setActive(d.isActive());
            device.setPictures(d.getPictures().stream().map(p -> "/api/picture/" + p.getId()).collect(Collectors.toList()));
            return device;
        });
    }

    @GetMapping("{id}")
    public DeviceDto getDevice(@PathVariable Long id) {
        return deviceService.findById(id).map(d -> {
            DeviceDto device = new DeviceDto();
            device.setId(d.getId());
            device.setX(d.getX());
            device.setY(d.getY());
            device.setName(d.getName());
            device.setActive(d.isActive());
            device.setPictures(d.getPictures().stream().map(p -> "/api/picture/" + p.getId()).collect(Collectors.toList()));
            return device;
        }).orElseThrow(() -> new RuntimeException("Not Fount"));
    }

    @PostMapping("{id}/toggle")
    public void toggleDevice(@PathVariable Long id) {
        Optional<Device> device = deviceService.findById(id);

        if (device.isEmpty()) {
            throw new RuntimeException("Device with id = " + id + " not found");
        }

        deviceService.toggle(id);
        webSocketHandler.post(id, device.get().isActive());
    }
}
