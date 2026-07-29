package com.owlet.api.storage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class ObjectKeyBuilder {

    public String build(
            String mimeType,
            String entityClass,
            UUID entityId,
            String originalFilename) {

        String folder = resolveFolder(mimeType);

        String extension = FilenameUtils.getExtension(originalFilename);

        LocalDate today = LocalDate.now();

        return "%s/%s/%04d/%02d/%02d/%s/%s.%s"
                .formatted(
                        folder,
                        entityClass,
                        today.getYear(),
                        today.getMonthValue(),
                        today.getDayOfMonth(),
                        entityId,
                        UUID.randomUUID(),
                        extension);

    }

    private String resolveFolder(String mimeType) {

        if (mimeType.startsWith("image/"))
            return "image";

        if (mimeType.startsWith("video/"))
            return "video";

        if (mimeType.equals("application/pdf"))
            return "pdf";

        if (mimeType.contains("word"))
            return "word";

        if (mimeType.contains("excel"))
            return "excel";

        return "other";

    }

}