package com.owlet.api.domain.ref;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "reference_type", schema = "ref")
public class ReferenceType {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    //TODO [Reverse Engineering] generate columns from DB
}