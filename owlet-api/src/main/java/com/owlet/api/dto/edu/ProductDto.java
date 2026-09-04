package com.owlet.api.dto.edu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto extends BaseDto<UUID> {


    private String code;

    private String title;

    private String shortDescription;

    private String description;

    private Boolean active;

    private Integer versionNo;

    private String educationalConcepts;

    private Map<String, Object> developedSkills;

    private Integer inventory;

    private Long price;

    private Long discountPrice;

    private OffsetDateTime discountActivationDate;

}