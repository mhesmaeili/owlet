package com.owlet.api.repository.base;

import com.owlet.api.domain.base.Attachment;

import java.util.List;
import java.util.UUID;

public interface AttachmentRepository extends BaseRepository<Attachment, UUID> {
    List<Attachment> findByEntityClassAndEntityIdAndDeletedFalse(
            String entityClass,
            UUID entityId);
}
