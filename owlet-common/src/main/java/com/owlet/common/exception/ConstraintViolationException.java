package com.owlet.common.exception;

public class ConstraintViolationException extends BaseException {
    public ConstraintViolationException(String message) {
        super(400, message);
    }
}
