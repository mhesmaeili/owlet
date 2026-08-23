package com.owlet.api.repository.edu;

import com.owlet.api.domain.edu.AssessmentTemplate;
import com.owlet.api.repository.base.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface AssessmentTemplateRepository extends BaseRepository<AssessmentTemplate, UUID> {

    List<AssessmentTemplate> findByProduct_IdAndSessionType_IdAndActiveTrue(UUID productId, UUID sessionTypeId);
}
