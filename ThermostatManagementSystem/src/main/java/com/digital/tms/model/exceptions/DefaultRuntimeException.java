package com.digital.tms.model.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public abstract class DefaultRuntimeException extends RuntimeException {

    private String defaultMessage = "custom exception";
    private HttpStatus status;

    public DefaultRuntimeException() {
        super();
    }

    public DefaultRuntimeException(String message, HttpStatus status) {
        super(message);
        this.defaultMessage = message;
        this.status = status;
    }
}
