package com.owlet.api.storage.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Optional;

@Component
public class ThumbnailGenerator {

    private static final int MAX_WIDTH = 320;
    private static final int MAX_HEIGHT = 320;

    // جلوگیری از decode تصاویر با ابعاد بسیار بزرگ
    private static final long MAX_SOURCE_PIXELS = 40_000_000L;

    public Optional<byte[]> generate(MultipartFile file) throws IOException {

        try (InputStream input = file.getInputStream();
             ImageInputStream imageInput =
                     ImageIO.createImageInputStream(input)) {

            if (imageInput == null) {
                return Optional.empty();
            }

            Iterator<ImageReader> readers =
                    ImageIO.getImageReaders(imageInput);

            if (!readers.hasNext()) {
                return Optional.empty();
            }

            ImageReader reader = readers.next();

            try {
                reader.setInput(imageInput, true, true);

                int width = reader.getWidth(0);
                int height = reader.getHeight(0);

                if (width <= 0 || height <= 0
                        || (long) width * height > MAX_SOURCE_PIXELS) {

                    throw new IOException(
                            "Image dimensions exceed the allowed limit"
                    );
                }

                // برای تصاویر متحرک، فقط فریم اول
                BufferedImage source = reader.read(0);

                if (source == null) {
                    throw new IOException("Cannot decode image");
                }

                try {
                    return Optional.of(resize(source));
                } finally {
                    source.flush();
                }

            } finally {
                reader.dispose();
            }
        }
    }

    private byte[] resize(BufferedImage source) throws IOException {

        double scale = Math.min(
                1.0,
                Math.min(
                        (double) MAX_WIDTH / source.getWidth(),
                        (double) MAX_HEIGHT / source.getHeight()
                )
        );

        int width = Math.max(
                1,
                (int) Math.round(source.getWidth() * scale)
        );

        int height = Math.max(
                1,
                (int) Math.round(source.getHeight() * scale)
        );

        BufferedImage thumbnail = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_ARGB
        );

        try {
            Graphics2D graphics = thumbnail.createGraphics();

            try {
                graphics.setRenderingHint(
                        RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BICUBIC
                );

                graphics.setRenderingHint(
                        RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY
                );

                graphics.drawImage(
                        source,
                        0,
                        0,
                        width,
                        height,
                        null
                );

            } finally {
                graphics.dispose();
            }

            try (ByteArrayOutputStream output =
                         new ByteArrayOutputStream()) {

                if (!ImageIO.write(thumbnail, "png", output)) {
                    throw new IOException("PNG writer is unavailable");
                }

                return output.toByteArray();
            }

        } finally {
            thumbnail.flush();
        }
    }
}
