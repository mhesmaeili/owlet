package com.owlet.common.exception;


import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Map;

@Getter
@Builder
public class ApiError {

    private OffsetDateTime timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

    private Map<String, Object> details;
}
