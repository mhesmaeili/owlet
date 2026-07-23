package com.owlet.api.repository.ref;

import com.owlet.api.domain.ref.ReferenceType;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReferenceTypeRepository
        extends BaseRepository<ReferenceType, UUID> {
}
