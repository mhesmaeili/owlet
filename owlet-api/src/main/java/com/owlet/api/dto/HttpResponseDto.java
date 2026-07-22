package com.owlet.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.owlet.api.enums.StatusType;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class HttpResponseDto<T> implements Serializable {
    private String message;
    private T data;
    private HttpStatus httpStatus;
    private Integer statusCode;
    private StatusType statusType;
    private Map<String, Object> additionalInfo;

    public HttpResponseDto(HttpStatus httpStatus, StatusType statusType, String message, T data, Map<String, Object> additionalInfo) {
        this.httpStatus = httpStatus;
        this.statusType = statusType;
        this.statusCode = statusType.getId();
        this.message = message;
        this.data = data;
        this.additionalInfo = additionalInfo;
    }
}
