package com.owlet.api.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contact_message", schema = "base")
public class ContactMessage extends UuidEntity {


    @NotNull
    @Column(name = "full_name", nullable = false, length = Integer.MAX_VALUE)
    private String fullName;

    @NotNull
    @Column(name = "phone_number", nullable = false, length = Integer.MAX_VALUE)
    private String phoneNumber;

    @Column(name = "message", length = Integer.MAX_VALUE)
    private String message;

    @Builder.Default
    private Boolean isRead = false;

}