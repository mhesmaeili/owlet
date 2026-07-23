package com.owlet.common.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final int status;

    protected BaseException(
            int status,
            String message) {

        super(message);
        this.status = status;
    }

}
