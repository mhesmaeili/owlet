package com.owlet.api.repository.base;

import com.owlet.api.domain.base.Attachment;
import com.owlet.api.domain.base.AttachmentReference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttachmentReferenceRepository extends BaseRepository<AttachmentReference, UUID> {
    List<AttachmentReference> findByEntityClassAndEntityIdAndDeletedFalse(
            String entityClass,
            UUID entityId);

    boolean existsByAttachmentAndDeletedFalse(Attachment entity);

    @Query("SELECT MAX(a.createdAt) FROM AttachmentReference a " +
            "WHERE a.entityClass = 'SessionStudent' " +
            "AND a.entityId IN (SELECT ss.id FROM SessionStudent ss WHERE ss.student.id = :studentId)")
    Optional<OffsetDateTime> findLastPhotoDateByStudentId(@Param("studentId") UUID studentId);

    @Query("SELECT a FROM AttachmentReference a " +
            "WHERE a.entityClass = 'SessionStudent' " +
            "AND a.entityId IN (SELECT ss.id FROM SessionStudent ss WHERE ss.student.id = :studentId)")
    List<AttachmentReference> findGalleryOfStudent(UUID studentId);
}
