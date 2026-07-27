package com.owlet.api.dto.edu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCreateRequest {


    private String code;

    @NotNull
    private String title;

    @NotBlank
    private String shortDescription;

    private String description;

    @NotNull
    private Boolean active;

    @NotNull
    private Integer versionNo;

}