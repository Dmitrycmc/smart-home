package io.github.dmitrycmc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import io.github.dmitrycmc.service.PictureService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/picture")
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{pictureId}")
    public void getPicture(@PathVariable long pictureId,
                                HttpServletResponse response) throws IOException {
        Optional<String> opt = pictureService.getPictureContentType(pictureId);
        if (opt.isPresent()) {
            response.setContentType(opt.get());
            response.getOutputStream().write(pictureService.getPictureDataById(pictureId).get());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping("/{pictureId}/{size}")
    public void getResizedPicture(
            @PathVariable long pictureId,
            @PathVariable int size,
            HttpServletResponse response
    ) throws IOException {
        Optional<String> opt = pictureService.getPictureContentType(pictureId);
        if (opt.isPresent()) {
            response.setContentType(opt.get());
            response.getOutputStream().write(pictureService.getPictureDataById(pictureId, size).get());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
