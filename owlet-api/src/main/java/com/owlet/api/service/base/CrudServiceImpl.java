package com.owlet.api.service.base;

import com.owlet.api.domain.base.BaseEntity;
import com.owlet.api.mapper.base.CrudMapper;
import com.owlet.api.repository.base.BaseRepository;
import com.owlet.api.repository.specification.FilterNode;
import com.owlet.api.repository.specification.SearchCriteria;
import com.owlet.api.repository.specification.SpecificationBuilder;
import com.owlet.api.security.AuditableService;
import com.owlet.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        UPDATE,
        REPOSITORY extends BaseRepository<ENTITY, ID>,
        MAPPER extends CrudMapper<ENTITY, DTO, CREATE, UPDATE>>
        implements CrudService<ID, DTO, CREATE, UPDATE> {

    protected final REPOSITORY repository;
    protected final MAPPER mapper;
    protected final AuditableService auditableService;

    @Override
    @Transactional(readOnly = true)
    public DTO get(ID id) {
        ENTITY entity = findEntity(id);
        return beforeReturn(toDto(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTO> getAll() {
        return toDto(findAllEntities());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DTO> getAll(Pageable pageable) {
        return repository.findAllByDeletedFalse(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DTO> search(String keyword, Pageable pageable) {
        return searchAdvanced(keyword, null, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DTO> searchAdvanced(String keyword, FilterNode filterTree, Pageable pageable) {
        // فیلتر پایه: حذف‌نشده‌ها
        Specification<ENTITY> spec = (root, query, cb) -> cb.equal(root.get("deleted"), false);

        // ۱. اعمال درخت فیلترهای پویا
        if (filterTree != null) {
            Specification<ENTITY> treeSpec = SpecificationBuilder.byNode(filterTree);
            if (treeSpec != null) {
                spec = spec.and(treeSpec);
            }
        }

        // ۲. اعمال جستجوی متنی (Keyword)
        Specification<ENTITY> keywordSpec = buildSearchSpecification(keyword);
        if (keywordSpec != null) {
            spec = spec.and(keywordSpec);
        }

        return repository.findAll(spec, pageable).map(mapper::toDto);
    }

    @Override
    public DTO create(CREATE dto) {
        validateCreate(dto);
        beforeCreate(dto);
        ENTITY entity = mapper.toEntity(dto);
        entity = beforeCreateSave(entity, dto);
        entity = saveEntity(entity);
        afterCreate(entity);
        return beforeReturn(toDto(entity));
    }

    @Override
    public List<DTO> create(List<CREATE> list) {
        List<ENTITY> entities = list.stream().map(mapper::toEntity).toList();
        entities = repository.saveAll(entities);
        return mapper.toDto(entities);
    }

    @Override
    public DTO update(ID id, UPDATE dto) {
        ENTITY entity = findEntity(id);
        validateUpdate(entity, dto);
        beforeUpdate(entity, dto);
        mapper.update(dto, entity);
        entity = beforeUpdateSave(entity, dto);
        entity = saveEntity(entity);
        afterUpdate(entity);
        return beforeReturn(toDto(entity));
    }

    @Override
    public void delete(ID id) {
        ENTITY entity = findEntity(id);
        beforeDelete(entity);
        doDelete(entity);
        afterDelete(entity);
    }

    @Override
    public boolean exists(ID id) {
        return repository.existsByIdAndDeletedFalse(id);
    }

    @Override
    public long count() {
        return repository.countByDeletedFalse();
    }

    protected ENTITY findEntity(ID id) {
        return repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException(entityClass().getSimpleName() + " not found : " + id));
    }

    protected List<ENTITY> findAllEntities() {
        return repository.findAllByDeletedFalse();
    }

    protected ENTITY saveEntity(ENTITY entity) {
        return repository.save(entity);
    }

    protected DTO toDto(ENTITY entity) {
        return mapper.toDto(entity);
    }

    protected List<DTO> toDto(List<ENTITY> entities) {
        return mapper.toDto(entities);
    }

    protected void doDelete(ENTITY entity) {
        if (isSoftDelete()) {
            entity.setDeleted(true);
            entity.setDeletedAt(auditableService.now());
            entity.setDeletedBy(auditableService.currentUserId());
            repository.save(entity);
        } else {
            repository.delete(entity);
        }
    }

    // متدهای هوک (Hooks)
    protected void beforeCreate(CREATE dto) {}
    protected void afterCreate(ENTITY entity) {}
    protected void beforeUpdate(ENTITY entity, UPDATE dto) {}
    protected void afterUpdate(ENTITY entity) {}
    protected void beforeDelete(ENTITY entity) {}
    protected void afterDelete(ENTITY entity) {}
    protected ENTITY beforeCreateSave(ENTITY entity, CREATE dto) { return entity; }
    protected ENTITY beforeUpdateSave(ENTITY entity, UPDATE dto) { return entity; }
    protected DTO beforeReturn(DTO dto) { return dto; }
    protected void validateCreate(CREATE dto) {}
    protected void validateUpdate(ENTITY entity, UPDATE dto) {}
    protected boolean isSoftDelete() { return true; }

    protected abstract Class<ENTITY> entityClass();

    // متدهای مربوط به جستجو
    protected Specification<ENTITY> buildSearchSpecification(String keyword) {
        if (keyword == null || keyword.trim().isBlank()) {
            return null;
        }
        String[] fields = getSearchableFields();
        if (fields == null || fields.length == 0) {
            return null;
        }
        return SpecificationBuilder.contains(keyword, fields);
    }

    protected String[] getSearchableFields() {
        return new String[0];
    }
}