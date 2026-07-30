package com.owlet.api.service.base.impl;

import com.owlet.api.domain.base.AttachmentReference;
import com.owlet.api.dto.base.AttachmentDto;
import com.owlet.api.dto.base.AttachmentReferenceCreateRequest;
import com.owlet.api.dto.base.AttachmentReferenceDto;
import com.owlet.api.dto.base.AttachmentUrlDto;
import com.owlet.api.mapper.base.AttachmentReferenceMapper;
import com.owlet.api.repository.base.AttachmentReferenceRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.AttachmentReferenceService;
import com.owlet.api.service.base.AttachmentService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.base.helper.EntityIdDto;
import com.owlet.api.storage.StorageObject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AttachmentReferenceServiceImpl extends CrudServiceImpl<
        AttachmentReference,
        UUID,
        AttachmentReferenceDto,
        AttachmentReferenceCreateRequest,
        AttachmentReferenceCreateRequest,
        AttachmentReferenceRepository,
        AttachmentReferenceMapper>
        implements AttachmentReferenceService {

    private final AttachmentService attachmentService;

    public AttachmentReferenceServiceImpl(
            AttachmentReferenceRepository repository,
            AttachmentReferenceMapper mapper,
            AuditableService auditableService, AttachmentService attachmentService) {

        super(repository, mapper, auditableService);
        this.attachmentService = attachmentService;
    }

    @Override
    protected Class<AttachmentReference> entityClass() {
        return AttachmentReference.class;
    }

    @Override
    public AttachmentReferenceDto upload(MultipartFile file, AttachmentReferenceCreateRequest request) {
        AttachmentDto attachmentDto = attachmentService.upload(file, request);
        request.setAttachment(new EntityIdDto(attachmentDto.getId()));
        return create(request);
    }

    @Override
    public List<AttachmentReferenceDto> list(String entityClass, UUID entityId) {
        return repository
                .findByEntityClassAndEntityIdAndDeletedFalse(
                        entityClass,
                        entityId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }


    @Override
    public StorageObject download(UUID id) {
        AttachmentReference attachmentReference =
                repository.findByIdAndDeletedFalse(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "AttachmentReference not found"));

        return attachmentService.download(attachmentReference.getAttachment());
    }

    @Override
    protected void afterDelete(AttachmentReference entity) {
        attachmentService.delete(entity.getAttachment().getId());
    }

    @Override
    public AttachmentUrlDto generatePresignedUrl(AttachmentReferenceDto attachmentReferenceDto) {
        Duration duration = Duration.ofMinutes(2);

        String url = attachmentService.generatePresignedUrl(
                attachmentReferenceDto.getAttachment().getObjectKey(),
                duration);

        AttachmentDto attachment = attachmentReferenceDto.getAttachment();

        return AttachmentUrlDto.builder()
                .attachmentId(attachment.getId())
                .filename(attachment.getFilename())
                .contentType(attachment.getMimeType())
                .size(attachment.getSize())
                .expiresAt(Instant.now().plus(duration))
                .url(url)
                .build();

    }
}