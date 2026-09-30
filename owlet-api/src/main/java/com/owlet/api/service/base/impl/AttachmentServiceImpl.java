package com.owlet.api.service.base.impl;

import com.owlet.api.domain.base.Attachment;
import com.owlet.api.dto.base.AttachmentCreateRequest;
import com.owlet.api.dto.base.AttachmentDto;
import com.owlet.api.dto.base.AttachmentReferenceCreateRequest;
import com.owlet.api.mapper.base.AttachmentMapper;
import com.owlet.api.repository.base.AttachmentReferenceRepository;
import com.owlet.api.repository.base.AttachmentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.AttachmentService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.storage.ObjectKeyBuilder;
import com.owlet.api.storage.StorageObject;
import com.owlet.api.storage.service.StorageService;
import com.owlet.api.storage.service.ThumbnailGenerator;
import com.owlet.common.exception.ConstraintViolationException;
import com.owlet.common.exception.StorageException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.HexFormat;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class AttachmentServiceImpl extends CrudServiceImpl<
        Attachment,
        UUID,
        AttachmentDto,
        AttachmentCreateRequest,
        AttachmentCreateRequest,
        AttachmentRepository,
        AttachmentMapper>
        implements AttachmentService {

    private final StorageService storageService;

    private final ObjectKeyBuilder objectKeyBuilder;
    private final AttachmentReferenceRepository attachmentReferenceRepository;
    private final ThumbnailGenerator thumbnailGenerator;

    public AttachmentServiceImpl(
            AttachmentRepository repository,
            AttachmentMapper mapper,
            AuditableService auditableService, StorageService storageService, ObjectKeyBuilder objectKeyBuilder, AttachmentReferenceRepository attachmentReferenceRepository, ThumbnailGenerator thumbnailGenerator) {

        super(repository, mapper, auditableService);
        this.storageService = storageService;
        this.objectKeyBuilder = objectKeyBuilder;
        this.attachmentReferenceRepository = attachmentReferenceRepository;
        this.thumbnailGenerator = thumbnailGenerator;
    }

    @Override
    protected Class<Attachment> entityClass() {
        return Attachment.class;
    }

    @Override
    @Transactional
    public AttachmentDto upload(
            MultipartFile file,
            AttachmentReferenceCreateRequest request) {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must not be empty");
        }

        try {
            // محاسبه هش قبل از آپلود؛ فایل کامل وارد حافظه نمی‌شود.
            String sha256 = calculateSha256(file);

            Optional<Attachment> existing =
                    repository
                            .findFirstBySha256AndDeletedFalseAndThumbnailFalse(
                                    sha256
                            );

            if (existing.isPresent()) {
                return mapper.toDto(existing.get());
            }

            // قبل از ایجاد object در MinIO، تصویر بررسی و کوچک می‌شود.
            Optional<byte[]> thumbnailBytes =
                    thumbnailGenerator.generate(file);

            String contentType = file.getContentType();

            if (contentType == null || contentType.isBlank()) {
                contentType = "application/octet-stream";
            }

            String filename = file.getOriginalFilename();

            if (filename == null || filename.isBlank()) {
                filename = "file";
            }

            String objectKey = objectKeyBuilder.build(
                    contentType,
                    request.getEntityClass(),
                    request.getEntityId(),
                    filename
            );

            // هر آپلود کلید اختصاصی دارد تا cleanup فایل قبلی را حذف نکند.
            //String objectKey = baseKey + "-" + UUID.randomUUID();

            List<String> createdObjectKeys = new ArrayList<>();

            registerRollbackCleanup(createdObjectKeys);

            // قبل از فراخوانی ثبت می‌کنیم؛ ممکن است سرویس فایل را
            // ذخیره کند ولی پاسخ به برنامه نرسد.
            createdObjectKeys.add(objectKey);

            try (InputStream input = file.getInputStream()) {
                storageService.upload(
                        input,
                        file.getSize(),
                        objectKey,
                        contentType
                );
            }

            UUID thumbnailId = null;

            if (thumbnailBytes.isPresent()) {
                byte[] bytes = thumbnailBytes.get();

                String thumbnailKey =
                        FilenameUtils.removeExtension(objectKey) + "-thumbnail.png";

                createdObjectKeys.add(thumbnailKey);

                try (InputStream input =
                             new ByteArrayInputStream(bytes)) {

                    storageService.upload(
                            input,
                            bytes.length,
                            thumbnailKey,
                            "image/png"
                    );
                }

                Attachment thumbnail = new Attachment();

                thumbnail.setFilename("thumbnail.png");
                thumbnail.setMimeType("image/png");
                thumbnail.setSize((long) bytes.length);
                thumbnail.setObjectKey(thumbnailKey);
                thumbnail.setSha256(calculateSha256(bytes));
                thumbnail.setThumbnail(true);
                thumbnail.setThumbnailId(null);

                // در صورت داشتن فیلدهای اجباری دیگر،
                // مانند ownerService یا status، اینجا مقداردهی شوند.

                Attachment savedThumbnail =
                        repository.save(thumbnail);

                thumbnailId = savedThumbnail.getId();
            }

            Attachment attachment = new Attachment();

            attachment.setFilename(filename);
            attachment.setMimeType(contentType);
            attachment.setSize(file.getSize());
            attachment.setObjectKey(objectKey);
            attachment.setSha256(sha256);
            attachment.setThumbnail(false);
            attachment.setThumbnailId(thumbnailId);

            // فیلدهای اجباری دیگر موجود در entity را اینجا مقداردهی کن.

            Attachment savedAttachment =
                    repository.saveAndFlush(attachment);

            return mapper.toDto(savedAttachment);

        } catch (Exception ex) {
            // پاک‌سازی MinIO در callback مربوط به rollback انجام می‌شود.
            throw new StorageException("Upload failed", ex);
        }
    }

    private String calculateSha256(MultipartFile file) throws Exception {

        MessageDigest digest =
                MessageDigest.getInstance("SHA-256");

        try (InputStream input = file.getInputStream();
             DigestInputStream digestInput =
                     new DigestInputStream(input, digest)) {

            digestInput.transferTo(OutputStream.nullOutputStream());
        }

        return HexFormat.of().formatHex(digest.digest());
    }

    private String calculateSha256(byte[] bytes) throws Exception {

        MessageDigest digest =
                MessageDigest.getInstance("SHA-256");

        return HexFormat.of().formatHex(digest.digest(bytes));
    }

    private void registerRollbackCleanup(List<String> objectKeys) {

        if (!TransactionSynchronizationManager.isActualTransactionActive()
                || !TransactionSynchronizationManager
                .isSynchronizationActive()) {

            throw new IllegalStateException(
                    "Upload must be invoked through a Spring transactional proxy"
            );
        }

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronization() {

                    @Override
                    public void afterCompletion(int status) {

                        if (status != STATUS_ROLLED_BACK) {
                            return;
                        }

                        for (int i = objectKeys.size() - 1; i >= 0; i--) {
                            String key = objectKeys.get(i);

                            try {
                                storageService.delete(key);

                            } catch (Exception cleanupException) {
                                log.error(
                                        "Cannot delete rolled-back object: {}",
                                        key,
                                        cleanupException
                                );
                            }
                        }
                    }
                }
        );
    }

    @Override
    protected void doDelete(Attachment entity) {
        if (!attachmentReferenceRepository.existsByAttachmentAndDeletedFalse(entity)) {
            storageService.delete(
                    entity.getObjectKey());
            super.doDelete(entity);
        }
    }

    @Override
    public StorageObject download(Attachment attachment) {

        StorageObject storageObject =
                storageService.download(
                        attachment.getObjectKey());

        return new StorageObject(

                storageObject.inputStream(),

                attachment.getFilename(),

                attachment.getMimeType(),

                attachment.getSize(),

                storageObject.etag(),

                storageObject.lastModified()

        );

    }

    @Override
    public String generatePresignedUrl(String objectKey, Duration duration) {
        return storageService.generatePresignedUrl(
                objectKey,
                duration);
    }
}