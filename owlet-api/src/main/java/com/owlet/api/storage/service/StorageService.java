package com.owlet.api.storage.service;

import com.owlet.api.storage.StorageObject;

import java.io.InputStream;
import java.security.DigestInputStream;

public interface StorageService {

    String upload(
            DigestInputStream dis,
            long size,
            String objectKey,
            String contentType);

    StorageObject download(
            String objectKey);

    void delete(
            String objectKey);

    boolean exists(
            String objectKey);

}