package com.owlet.api.storage.service;

import com.owlet.api.storage.MinioProperties;
import com.owlet.api.storage.StorageObject;
import com.owlet.common.exception.StorageException;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.DigestInputStream;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class MinioStorageService implements StorageService {

    private final MinioClient minioClient;
    private final MinioProperties properties;

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

            return minioClient.getPresignedObjectUrl(

                    GetPresignedObjectUrlArgs.builder()
                            .method(Http.Method.GET)
                            .bucket(properties.bucket())
                            .object(objectKey)
                            .expiry((int) duration.toSeconds())
                            .build());

        } catch (Exception ex) {

            throw new StorageException(
                    "Cannot generate url",
                    ex);

        }

    }
}
