package com.owlet.api.exception;

import com.owlet.api.dto.FieldErrorDto;
import com.owlet.common.exception.BaseException;
import com.owlet.common.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Void>> handle(
            BaseException ex) {

        return ResponseEntity
                .status(ex.getStatus())
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<List<FieldErrorDto>>> handleValidation(MethodArgumentNotValidException ex) {

        List<FieldErrorDto> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorDto(
                        error.getField(),
                        error.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error("اعتبار سنجی داده های ورودی مشکل دارد", errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handle(Exception ex) {

        ex.printStackTrace();

        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.error("Internal Server Error"));
    }

}
