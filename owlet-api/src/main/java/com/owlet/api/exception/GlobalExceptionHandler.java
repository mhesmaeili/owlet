package com.owlet.api.exception;

import com.owlet.common.exception.BaseException;
import com.owlet.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Void>> handle(
            BaseException ex) {

        return ResponseEntity
                .status(ex.getStatus())
                .body(ApiResponse.error(ex.getMessage()));
    }

}
