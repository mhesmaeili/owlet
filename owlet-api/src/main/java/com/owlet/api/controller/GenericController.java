package com.owlet.api.controller;

import com.owlet.api.domain.base.BaseEntity;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.HttpResponseDto;
import com.owlet.api.enums.StatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;

public class GenericController <E extends BaseEntity<?>, PK extends Serializable, D extends BaseDto<?>>
        implements Serializable{

    @PostMapping("/getAllPage")
    public ResponseEntity<HttpResponseDto<Page<D>>> getAllPage(@PageableDefault(sort = {"createdAt", "updatedAt"}, direction = Sort.Direction.DESC) Pageable pageable){
        PageImpl<D> response = new PageImpl<>(new ArrayList<>(), pageable, 0);
        return ResponseEntity.ok(new HttpResponseDto<Page<D>>(HttpStatus.OK, StatusType.SUCCESSFUL,
                "",
                response,
                null));
    }
}
