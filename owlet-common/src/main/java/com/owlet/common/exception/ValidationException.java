package com.owlet.common.exception;

public class ValidationException extends BaseException {
    public ValidationException(String message) {
        super(400, message);
    }
}
