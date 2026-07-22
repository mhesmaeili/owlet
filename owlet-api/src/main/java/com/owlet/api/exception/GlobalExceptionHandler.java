package com.owlet.api.exception;

import com.owlet.common.exception.ApiError;
import com.owlet.common.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handle(
            BusinessException ex
    ) {

        return ResponseEntity
                .badRequest()
                .body(
                        ApiError.builder()
                                .status(1)
                                .message(ex.getMessage())
                                .timestamp(OffsetDateTime.now())
                                .build()
                );
    }
}
