package io.github.dmitrycmc.utils;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PictureUtils {
    private static BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getBytes(BufferedImage buf, String extension) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(buf, extension, baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }

    public static byte[] resizeImage(byte[] img, String extension, int size) {
        BufferedImage buf = createImageFromBytes(img);
        BufferedImage scaledImageBuf = Scalr.resize(buf, size);
        return getBytes(scaledImageBuf, extension);
    }
}
