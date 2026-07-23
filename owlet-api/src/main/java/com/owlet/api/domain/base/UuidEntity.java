package com.owlet.api.domain.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class UuidEntity extends BaseEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
