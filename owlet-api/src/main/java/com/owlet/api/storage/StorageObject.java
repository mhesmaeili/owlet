package com.owlet.api.storage;

import java.io.InputStream;
import java.time.Instant;

public record StorageObject(

        InputStream inputStream,

        String filename,

        String contentType,

        long contentLength,

        String etag,

        Instant lastModified

) {
}