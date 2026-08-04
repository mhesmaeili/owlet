package com.owlet.common.exception;

public class BadCredentialsException extends BaseException {
    public BadCredentialsException(String message) {
        super(401, message);
    }
}
