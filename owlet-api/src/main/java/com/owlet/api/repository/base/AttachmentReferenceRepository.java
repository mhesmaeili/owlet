package com.owlet.api.repository.base;

import com.owlet.api.domain.base.Attachment;
import com.owlet.api.domain.base.AttachmentReference;

import java.util.List;
import java.util.UUID;

public interface AttachmentReferenceRepository extends BaseRepository<AttachmentReference, UUID> {
    List<AttachmentReference> findByEntityClassAndEntityIdAndDeletedFalse(
            String entityClass,
            UUID entityId);

    boolean existsByAttachmentAndDeletedFalse(Attachment entity);
}
