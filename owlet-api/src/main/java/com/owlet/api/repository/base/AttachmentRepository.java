package com.owlet.api.repository.base;

import com.owlet.api.domain.base.Attachment;

import java.util.Optional;
import java.util.UUID;

public interface AttachmentRepository extends BaseRepository<Attachment, UUID> {

    Optional<Attachment> findBySha256AndDeletedFalse(String sha256);
}
