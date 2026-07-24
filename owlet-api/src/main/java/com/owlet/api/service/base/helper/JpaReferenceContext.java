package com.owlet.api.service.base.helper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaReferenceContext {
    @PersistenceContext
    private EntityManager entityManager;

    public <T> T getReference(Class<T> type, Object id) {

        if (id == null) {
            return null;
        }

        return entityManager.getReference(type, id);
    }
}
