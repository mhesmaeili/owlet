package com.owlet.api.dto.org;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.service.base.helper.EntityIdDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchCreateRequest {

    private EntityIdDto school;

    private EntityIdDto managerAccount;

    private String title;

    private String phone;
}
