package com.digital.tms.model.exceptions;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class LoginException extends DefaultRuntimeException {

    public LoginException() {
        super(
                "username or password is incorrect",
                UNAUTHORIZED
        );
    }

    public LoginException(String message, HttpStatus status) {
        super(message, status);
    }
}
