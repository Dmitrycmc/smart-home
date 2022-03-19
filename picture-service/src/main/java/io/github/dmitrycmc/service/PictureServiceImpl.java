package io.github.dmitrycmc.service;

import io.github.dmitrycmc.dao.PictureDao;
import io.github.dmitrycmc.model.Picture;

import io.github.dmitrycmc.utils.PictureUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class PictureServiceImpl implements PictureService {

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);

    @Value("${picture.storage.path}")
    private String storagePath;

    private final PictureDao PictureDao;

    @Autowired
    public PictureServiceImpl(PictureDao PictureDao) {
        this.PictureDao = PictureDao;
    }

    @Override
    public Optional<String> getPictureContentType(long id) {
        return PictureDao.findById(id).map(Picture::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {
        return getPictureDataById(id, false);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id, boolean preview) {
        return PictureDao.findById(id)
                .map(pic -> Paths.get(storagePath, pic.getStorageFileName() + (preview ? "-preview" : "")))
                .filter(Files::exists)
                .map(path -> {
                    try {
                        return Files.readAllBytes(path);
                    } catch (IOException ex) {
                        logger.error("Can't read file", ex);
                        throw new RuntimeException(ex);
                    }
                });
    }

    @Override
    public String createPicture(byte[] pictureData, String contentType) {
        String extension = contentType.split("/")[1];
        byte[] previewData = PictureUtils.resizeImage(pictureData, extension, 160);

        String filename = UUID.randomUUID().toString();
        try (OutputStream os = Files.newOutputStream(Paths.get(storagePath, filename))) {
            os.write(pictureData);
        } catch (IOException ex) {
            logger.error("Can't write to file", ex);
            throw new RuntimeException(ex);
        }
        try (OutputStream os = Files.newOutputStream(Paths.get(storagePath, filename + "-preview"))) {
            os.write(previewData);
        } catch (IOException ex) {
            logger.error("Can't write to file", ex);
            throw new RuntimeException(ex);
        }
        return filename;
    }
}
