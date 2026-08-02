package com.owlet.api.dto.ses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.edu.ProductDto;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.org.ClassroomDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainingCourseDto extends BaseDto<UUID> {


    private ClassroomDto classroom;
    private AccountDto teacherAccount;
    private ProductDto product;


}
