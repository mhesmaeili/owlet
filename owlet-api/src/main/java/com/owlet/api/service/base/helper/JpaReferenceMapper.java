package com.owlet.api.service.base.helper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaReferenceMapper {

    private final JpaReferenceContext context;

    @Named("toReference")
    public <T> T toReference(
            EntityIdDto dto,
            @TargetType Class<T> entityClass) {

        if (dto == null || dto.getId() == null) {
            return null;
        }

        return context.getReference(entityClass, dto.getId());
    }
}
