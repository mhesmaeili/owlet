package com.owlet.api.dto.org;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.idm.AccountDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchDto extends BaseDto<UUID> {
    private SchoolDto school;


    private String code;


    private String title;


    private AccountDto managerAccount;


    private String phone;


    private String mobile;
}
