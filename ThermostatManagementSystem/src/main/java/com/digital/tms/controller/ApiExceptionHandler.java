package com.digital.tms.controller;

import com.digital.tms.model.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    record MethodNotFoundPayload(String message, String invalidField, String rejectedValue) {
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var errorMsg = "Field validation failed";
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(field -> new MethodNotFoundPayload(field.getDefaultMessage(), field.getField(), String.valueOf(field.getRejectedValue()))).toList();
        return ResponseEntity.badRequest()
                .body(new ErrorResponse
                        .ErrorResponseBuilder(BAD_REQUEST, errorMsg)
                        .data(errors)
                        .build());
    }

    @ExceptionHandler(value = {
            ThermostatAlreadyExistsException.class,
            EmailAlreadyExistsException.class,
            EntityNotFoundException.class,
            LoginException.class
    })
    public ResponseEntity<ErrorResponse> alreadyExistsExceptions(Exception e) {
        var exception = (DefaultRuntimeException) e;
        return ResponseEntity.status(exception.getStatus()).body(
                new ErrorResponse
                        .ErrorResponseBuilder(exception.getStatus(), exception.getDefaultMessage())
                        .build()
        );
    }


//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//
//    }
}
