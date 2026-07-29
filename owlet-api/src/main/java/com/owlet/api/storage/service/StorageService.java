package com.owlet.api.storage.service;

import java.io.InputStream;
import java.security.DigestInputStream;

public interface StorageService {

    String upload(
            DigestInputStream dis,
            long size,
            String objectKey,
            String contentType);

    InputStream download(
            String objectKey);

    void delete(
            String objectKey);

    boolean exists(
            String objectKey);

}