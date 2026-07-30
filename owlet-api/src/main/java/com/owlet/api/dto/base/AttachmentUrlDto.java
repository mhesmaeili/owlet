package com.owlet.api.dto.base;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record AttachmentUrlDto(

        UUID attachmentId,

        String filename,

        String contentType,

        Long size,

        Instant expiresAt,

        String url,

        String thumbnailUrl

) {
}
