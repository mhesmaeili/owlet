package com.owlet.api.dto.org;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.ref.ReferenceItemDto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcademicYearDto extends BaseDto<UUID> {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
