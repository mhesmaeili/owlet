package com.owlet.api.dto.org;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.ref.ReferenceItemDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolDto extends BaseDto<UUID> {
    private String code;

    private String title;
    private ReferenceItemDto schoolType;
    private AccountDto managerAccount;


    private String economicCode;

    private String phone;
    private String mobile;

    private String email;
    private String website;

    private String province;
    private String city;
    private String address;
    private String postalCode;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private Boolean active;

    private String description;
}
