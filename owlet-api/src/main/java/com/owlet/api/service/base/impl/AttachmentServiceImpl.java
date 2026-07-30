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
import com.owlet.common.exception.ConstraintViolationException;
import com.owlet.common.exception.StorageException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.HexFormat;
import java.util.UUID;

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

    public AttachmentServiceImpl(
            AttachmentRepository repository,
            AttachmentMapper mapper,
            AuditableService auditableService, StorageService storageService, ObjectKeyBuilder objectKeyBuilder, AttachmentReferenceRepository attachmentReferenceRepository) {

        super(repository, mapper, auditableService);
        this.storageService = storageService;
        this.objectKeyBuilder = objectKeyBuilder;
        this.attachmentReferenceRepository = attachmentReferenceRepository;
    }

    @Override
    protected Class<Attachment> entityClass() {
        return Attachment.class;
    }

    @Override
    public AttachmentDto upload(MultipartFile file, AttachmentReferenceCreateRequest request) {
        String objectKey = objectKeyBuilder.build(
                file.getContentType(),
                request.getEntityClass(),
                request.getEntityId(),
                file.getOriginalFilename());

        try {

            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            DigestInputStream dis =
                    new DigestInputStream(
                            file.getInputStream(),
                            digest);

            storageService.upload(
                    dis,
                    file.getSize(),
                    objectKey,
                    file.getContentType());

            byte[] hash = digest.digest();
            String sha256 = HexFormat.of().formatHex(hash);

            Attachment attachment = new Attachment();

            attachment.setFilename(file.getOriginalFilename());

            attachment.setMimeType(file.getContentType());

            attachment.setSize(file.getSize());

            attachment.setObjectKey(objectKey);

            attachment.setSha256(sha256);

            attachment = repository.save(attachment);

            return mapper.toDto(attachment);

        } catch (Exception ex) {

            try {
                storageService.delete(objectKey);
            } catch (Exception ignored) {
            }

            throw new StorageException(
                    "Upload failed",
                    ex);

        }
    }

    @Override
    protected void beforeDelete(Attachment entity) {
        if (attachmentReferenceRepository.existsByAttachmentAndDeletedFalse(entity)) {
            throw new ConstraintViolationException("Attachment reference already exists");
        }
        storageService.delete(
                entity.getObjectKey());
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