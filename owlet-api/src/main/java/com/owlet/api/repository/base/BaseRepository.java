package com.owlet.api.repository.base;

import com.owlet.api.domain.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<
        ENTITY extends BaseEntity<ID>,
        ID extends Serializable>
        extends JpaRepository<ENTITY, ID> {

}