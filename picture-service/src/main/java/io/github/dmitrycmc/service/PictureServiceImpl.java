package io.github.dmitrycmc.service;

import io.github.dmitrycmc.dao.PictureDao;
import io.github.dmitrycmc.model.Picture;

import io.github.dmitrycmc.utils.PictureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
        return PictureDao.findById(id)
                .map(pic -> Paths.get(storagePath, pic.getStorageFileName()))
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
    public Optional<byte[]> getPictureDataById(long id, int size) {
        return PictureDao.findById(id)
                .map(pic -> {
                    try {
                        return getResizedPictureWithCache(pic, size);
                    } catch (IOException ex) {
                        logger.error("Can't read file", ex);
                        throw new RuntimeException(ex);
                    }
                });
    }

    private void writePicture(byte[] data, Path filename) {
        try (OutputStream os = Files.newOutputStream(filename)) {
            os.write(data);
        } catch (IOException ex) {
            logger.error("Can't write to file", ex);
            throw new RuntimeException(ex);
        }
    }

    private byte[] getResizedPictureWithCache(Picture pic, int size) throws IOException {
        Path origFilename = Paths.get(storagePath, pic.getStorageFileName());
        Path newFilename = Paths.get(storagePath, pic.getStorageFileName() + "-" + size);

        if (Files.exists(newFilename)) {
            return Files.readAllBytes(newFilename);
        }

        byte[] pictureData = Files.readAllBytes(origFilename);
        String extension = pic.getContentType().split("/")[1];
        byte[] resizedData = PictureUtils.resizeImage(pictureData, extension, size);

        writePicture(resizedData, newFilename);

        return resizedData;
    }

    @Override
    public String createPicture(byte[] pictureData) {
        String filename = UUID.randomUUID().toString();
        writePicture(pictureData, Paths.get(storagePath, filename));
        return filename;
    }
}
