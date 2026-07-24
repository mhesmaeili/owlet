package com.owlet.api.repository.org;

import com.owlet.api.domain.org.Branch;
import com.owlet.api.domain.org.School;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BranchRepository
        extends BaseRepository<Branch, UUID> {
}
