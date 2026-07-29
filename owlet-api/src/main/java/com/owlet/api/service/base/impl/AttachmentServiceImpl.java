package com.owlet.api.service.base.impl;

import com.owlet.api.domain.base.Attachment;
import com.owlet.api.dto.base.AttachmentCreateRequest;
import com.owlet.api.dto.base.AttachmentDto;
import com.owlet.api.mapper.base.AttachmentMapper;
import com.owlet.api.repository.base.AttachmentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.AttachmentService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.storage.ObjectKeyBuilder;
import com.owlet.api.storage.service.StorageService;
import com.owlet.common.exception.StorageException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.List;
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

    public AttachmentServiceImpl(
            AttachmentRepository repository,
            AttachmentMapper mapper,
            AuditableService auditableService, StorageService storageService, ObjectKeyBuilder objectKeyBuilder) {

        super(repository, mapper, auditableService);
        this.storageService = storageService;
        this.objectKeyBuilder = objectKeyBuilder;
    }

    @Override
    protected Class<Attachment> entityClass() {
        return Attachment.class;
    }

    @Override
    public AttachmentDto upload(MultipartFile file, AttachmentCreateRequest request) {
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

            Attachment attachment = mapper.toEntity(request);

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
    public List<AttachmentDto> list(String entityClass, UUID entityId) {
        return repository
                .findByEntityClassAndEntityIdAndDeletedFalse(
                        entityClass,
                        entityId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    protected void beforeDelete(Attachment entity) {
        storageService.delete(
                entity.getObjectKey());

    }

    @Override
    public InputStream download(UUID id) {

        Attachment attachment =
                repository.findById(id)
                        .orElseThrow();

        return storageService.download(
                attachment.getObjectKey());

    }
}