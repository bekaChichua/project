package com.digital.tms.model.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends DefaultRuntimeException{
    public EntityNotFoundException() {
        super(
                "entity not found",
                HttpStatus.NOT_FOUND);
    }

    public EntityNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
