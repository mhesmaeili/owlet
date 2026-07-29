package com.owlet.api.storage;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BucketInitializer {

    private final MinioClient minioClient;
    private final MinioProperties properties;

    @PostConstruct
    public void init() {

        try {

            boolean exists =
                    minioClient.bucketExists(
                            BucketExistsArgs.builder()
                                    .bucket(properties.bucket())
                                    .build());

            if (!exists) {

                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(properties.bucket())
                                .build());

                log.info("Bucket '{}' created.", properties.bucket());

            } else {

                log.info("Bucket '{}' already exists.", properties.bucket());

            }

        } catch (Exception e) {

            throw new IllegalStateException(
                    "Cannot initialize MinIO bucket",
                    e);

        }

    }

}
