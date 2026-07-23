package com.owlet.common.exception;

public class MethodArgumentNotValidException extends BaseException {
    public MethodArgumentNotValidException(String message) {
        super(400, message);
    }
}
