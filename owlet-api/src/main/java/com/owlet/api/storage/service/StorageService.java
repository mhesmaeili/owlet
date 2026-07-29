package com.owlet.api.storage.service;

import java.io.InputStream;

public interface StorageService {

    String upload(
            InputStream inputStream,
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