package com.owlet.api.storage.service;

import com.owlet.api.storage.StorageObject;

import java.security.DigestInputStream;
import java.time.Duration;

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

    String generatePresignedUrl(
            String objectKey,
            Duration duration);
}