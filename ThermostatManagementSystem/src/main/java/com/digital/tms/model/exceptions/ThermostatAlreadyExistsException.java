package com.digital.tms.model.exceptions;

import org.springframework.http.HttpStatus;

public class ThermostatAlreadyExistsException extends DefaultRuntimeException {
    public ThermostatAlreadyExistsException(String name) {
        super(
                String.format("thermostat with name %s already exists", name),
                HttpStatus.CONFLICT);
    }

    public ThermostatAlreadyExistsException(String message, HttpStatus status) {
        super(message, status);
    }
}
