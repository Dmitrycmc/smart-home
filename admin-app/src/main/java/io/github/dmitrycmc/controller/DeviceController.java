package io.github.dmitrycmc.controller;

import io.github.dmitrycmc.model.Device;
import io.github.dmitrycmc.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public String getList(Model model) {
        List<Device> devices = deviceService.search();
        model.addAttribute("devices", devices);
        return "tables/devices";
    }
}
