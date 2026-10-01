package com.owlet.api.storage.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.AlphaComposite;
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

    private static final int TARGET_SIZE = 320;

    // فایل‌های کم‌حجم نیازی به thumbnail ندارند.
    private static final long MIN_SOURCE_BYTES = 32L * 1024;

    private static final long MAX_SOURCE_PIXELS = 40_000_000L;

    public Optional<byte[]> generate(MultipartFile file)
            throws IOException {

        long sourceBytes = file.getSize();

        if (file.isEmpty() || sourceBytes < MIN_SOURCE_BYTES) {
            return Optional.empty();
        }

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

                // برای پرکردن مربع، هر دو ضلع باید کافی باشند.
                // تصاویر کوچک را بزرگ نمی‌کنیم.
                if (Math.min(width, height) < TARGET_SIZE) {
                    return Optional.empty();
                }

                // تصویر از قبل اندازه مناسب دارد.
                if (width <= TARGET_SIZE && height <= TARGET_SIZE) {
                    return Optional.empty();
                }

                // برای تصاویر متحرک فقط فریم اول
                BufferedImage source = reader.read(0);

                if (source == null) {
                    throw new IOException("Cannot decode image");
                }

                try {
                    byte[] thumbnail = createThumbnail(source);

                    // خروجی فقط وقتی ارزش ذخیره‌سازی دارد که کوچک‌تر باشد.
                    if (thumbnail.length >= sourceBytes) {
                        return Optional.empty();
                    }

                    return Optional.of(thumbnail);

                } finally {
                    source.flush();
                }

            } finally {
                reader.dispose();
            }
        }
    }

    private byte[] createThumbnail(BufferedImage source)
            throws IOException {

        int side = Math.min(
                source.getWidth(),
                source.getHeight()
        );

        int x = (source.getWidth() - side) / 2;
        int y = (source.getHeight() - side) / 2;

        // مربع مرکزی؛ بدون تغییر نسبت ابعاد
        BufferedImage cropped = source.getSubimage(
                x,
                y,
                side,
                side
        );

        BufferedImage thumbnail = resizeProgressively(cropped);

        try {
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

    private BufferedImage resizeProgressively(BufferedImage source) {

        BufferedImage current = source;

        try {
            // کاهش اندازه مرحله‌ای؛ هیچ مرحله‌ای بزرگ‌نمایی نمی‌کند.
            // در حالت 320×320 نیز یک تصویر مستقل می‌سازیم.
            do {
                int nextSize = Math.max(
                        TARGET_SIZE,
                        current.getWidth() / 2
                );

                BufferedImage next = resizeStep(
                        current,
                        nextSize
                );

                if (current != source) {
                    current.flush();
                }

                current = next;

            } while (current.getWidth() > TARGET_SIZE);

            return current;

        } catch (RuntimeException | Error exception) {
            if (current != source) {
                current.flush();
            }

            throw exception;
        }
    }

    private BufferedImage resizeStep(
            BufferedImage source,
            int size) {

        BufferedImage target = new BufferedImage(
                size,
                size,
                BufferedImage.TYPE_INT_ARGB
        );

        try {
            Graphics2D graphics = target.createGraphics();

            try {
                graphics.setComposite(AlphaComposite.Src);

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
                        size,
                        size,
                        null
                );

            } finally {
                graphics.dispose();
            }

            return target;

        } catch (RuntimeException | Error exception) {
            target.flush();
            throw exception;
        }
    }
}