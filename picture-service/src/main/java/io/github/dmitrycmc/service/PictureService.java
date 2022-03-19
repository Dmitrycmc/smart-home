package io.github.dmitrycmc.service;

import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentType(long id);

    Optional<byte[]> getPictureDataById(long id);
    Optional<byte[]> getPictureDataById(long id, boolean preview);

    String createPicture(byte[] pictureData, String contentType);
}
