package com.owlet.api.storage.service;

import com.owlet.api.storage.MinioProperties;
import com.owlet.api.storage.StorageObject;
import com.owlet.common.exception.StorageException;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.net.URI;

import java.security.DigestInputStream;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class MinioStorageService implements StorageService {

    private final MinioClient minioClient;
    private final MinioProperties properties;
    @Value("${storage.minio.public.base.url}")
    private String publicBaseUrl;

    @Override
    public String upload(
            DigestInputStream dis,
            long size,
            String objectKey,
            String contentType) {

        try {

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(properties.bucket())
                            .object(objectKey)
                            .stream(dis, size, 10L * 1024 * 1024)
                            .contentType(contentType)
                            .build());

            return objectKey;

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Cannot upload file to MinIO",
                    ex);

        }

    }

    @Override
    public StorageObject download(String objectKey) {
        try {
            StatObjectResponse stat =
                    minioClient.statObject(
                            StatObjectArgs.builder()
                                    .bucket(properties.bucket())
                                    .object(objectKey)
                                    .build());

            GetObjectResponse stream =
                    minioClient.getObject(
                            GetObjectArgs.builder()
                                    .bucket(properties.bucket())
                                    .object(objectKey)
                                    .build());

            return new StorageObject(
                    stream,
                    objectKey,
                    stat.contentType(),
                    stat.size(),
                    stat.etag(),
                    stat.lastModified().toInstant());

        } catch (Exception ex) {

            throw new StorageException(
                    "Cannot download object",
                    ex);

        }

    }

    @Override
    public void delete(String objectKey) {

        try {

            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(properties.bucket())
                            .object(objectKey)
                            .build());

        } catch (Exception ex) {

            throw new RuntimeException(ex);

        }

    }

    @Override
    public boolean exists(String objectKey) {

        try {

            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(properties.bucket())
                            .object(objectKey)
                            .build());

            return true;

        } catch (Exception ex) {

            return false;

        }

    }

    @Override
    public String generatePresignedUrl(
            String objectKey,
            Duration duration) {

        try {
            // امضا با همان کلاینت و آدرس داخلی MinIO تولید می‌شود.
            String signedUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Http.Method.GET)
                            .bucket(properties.bucket())
                            .object(objectKey)
                            .expiry(Math.toIntExact(duration.toSeconds()))
                            .build());

            URI signedUri = URI.create(signedUrl);

            String baseUrl = publicBaseUrl.trim()
                    .replaceAll("/+$", "");

            URI publicUri = URI.create(baseUrl);

            if ((! "https".equalsIgnoreCase(publicUri.getScheme())
                    && ! "http".equalsIgnoreCase(publicUri.getScheme()))
                    || publicUri.getHost() == null
                    || publicUri.getRawUserInfo() != null
                    || publicUri.getRawQuery() != null
                    || publicUri.getRawFragment() != null) {

                throw new IllegalArgumentException(
                        "Invalid storage.minio.public-base-url");
            }

            // مسیر و query بدون decode یا encode مجدد منتقل می‌شوند.
            return baseUrl
                    + signedUri.getRawPath()
                    + (signedUri.getRawQuery() == null
                    ? ""
                    : "?" + signedUri.getRawQuery());

        } catch (Exception ex) {
            throw new StorageException(
                    "Cannot generate url",
                    ex);
        }
    }
}
