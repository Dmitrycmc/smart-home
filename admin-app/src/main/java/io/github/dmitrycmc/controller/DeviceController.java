package io.github.dmitrycmc.controller;

import io.github.dmitrycmc.dto.DeviceDto;
import io.github.dmitrycmc.model.Device;
import io.github.dmitrycmc.model.Picture;
import io.github.dmitrycmc.service.DeviceService;
import io.github.dmitrycmc.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/device")
public class DeviceController {

    private final DeviceService deviceService;
    private final PictureService pictureService;

    @Autowired
    public DeviceController(DeviceService deviceService, PictureService pictureService) {
        this.deviceService = deviceService;
        this.pictureService = pictureService;
    }

    @GetMapping
    public String getList(Model model) {
        List<Device> devices = deviceService.search();
        model.addAttribute("devices", devices);
        return "device/table";
    }

    @GetMapping(path = "/new")
    public String create(Model model) {
        model.addAttribute("device", new Device());
        return "device/form";
    }

    @PostMapping
    public String save(DeviceDto deviceDto) {
        System.out.println(deviceDto);

        Device device = new Device();

        device.setId(deviceDto.getId());
        device.setName(deviceDto.getName());

        if (deviceDto.getPictures() != null) {
            for (MultipartFile file: deviceDto.getPictures()) {
                try {
                    device.getPictures().add(new Picture(null,
                            file.getOriginalFilename(),
                            file.getContentType(),
                            pictureService.createPicture(file.getBytes()),
                            device
                    ));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        deviceService.save(device);
        return "redirect:device";
    }
}
