package com.owlet.api.dto;

public record FieldErrorDto(
        String field,
        String message
) {
}
