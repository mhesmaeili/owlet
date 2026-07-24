package com.owlet.api.dto.org;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.service.base.helper.EntityIdDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolCreateRequest {
    @NotBlank
    private String code;

    @NotBlank
    private String title;

    @NotNull
    private EntityIdDto schoolType;

    @Email
    private String email;

    private String website;

    private String phone;

    private String mobile;

    private EntityIdDto parentSchool;

    private EntityIdDto managerAccount;

    //private String economicCode;

    //private String province;

    //private String city;

    private String address;

    private String postalCode;

    //private BigDecimal latitude;

    //private BigDecimal longitude;

    private String description;
}
