package com.owlet.api.repository.ref;

import com.owlet.api.domain.ref.ReferenceItem;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReferenceItemRepository
        extends BaseRepository<ReferenceItem, UUID> {
    List<ReferenceItem> findByReferenceType_CodeAndActiveTrueOrderBySortOrderAsc(String typeCode);

    ReferenceItem findByReferenceTypeCodeAndCodeAndActiveTrue(String typeCode, String itemCode);
}
