package com.owlet.api.repository.base;

import com.owlet.api.domain.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<
        ENTITY extends BaseEntity<ID>,
        ID extends Serializable>
        extends JpaRepository<ENTITY, ID>,
        JpaSpecificationExecutor<ENTITY> {

    Optional<ENTITY> findByIdAndDeletedFalse(ID id);

    List<ENTITY> findAllByDeletedFalse();

    Page<ENTITY> findAllByDeletedFalse(Pageable pageable);

    boolean existsByIdAndDeletedFalse(ID id);

    long countByDeletedFalse();
}