package com.digital.tms.model.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends DefaultRuntimeException {
    public EmailAlreadyExistsException(String email) {
        super(
                String.format("user with email %s already exists", email),
                HttpStatus.CONFLICT);
    }

    public EmailAlreadyExistsException(String message, HttpStatus status) {
        super(message, status);
    }
}
