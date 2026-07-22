package com.owlet.api.service.base;

import com.owlet.api.domain.base.BaseEntity;
import com.owlet.api.mapper.base.CrudMapper;
import com.owlet.api.repository.base.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@Transactional
public abstract class CrudServiceImpl<
        ENTITY extends BaseEntity<ID>,
        ID extends Serializable,
        DTO,
        CREATE,
        UPDATE>
        implements CrudService<ID, DTO, CREATE, UPDATE> {

    protected final BaseRepository<ENTITY, ID> repository;

    protected final CrudMapper<
            ENTITY,
            DTO,
            CREATE,
            UPDATE> mapper;

    @Override
    @Transactional(readOnly = true)
    public DTO get(ID id) {

        ENTITY entity = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return mapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTO> getAll() {

        return mapper.toDto(repository.findAll());
    }

    @Override
    public DTO create(CREATE dto) {

        ENTITY entity = mapper.toEntity(dto);

        return mapper.toDto(repository.save(entity));
    }

    @Override
    public DTO update(ID id, UPDATE dto) {

        ENTITY entity = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        mapper.update(dto, entity);

        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(ID id) {

        repository.deleteById(id);
    }

}