package com.digital.tms.model.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class ErrorResponse {
    private ErrorResponse() {
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private int code;

    private String status;

    private String message;

    private Object data;

    private String stacktrace;

    private ErrorResponse(ErrorResponseBuilder build) {
        this.timestamp = build.timestamp;
        this.code = build.code;
        this.status = build.status;
        this.message = build.message;
        this.data = build.data;
        this.stacktrace = build.stacktrace;
    }

    public static class ErrorResponseBuilder {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private Date timestamp;
        private int code;
        private String status;
        private String message;
        private Object data;
        private String stacktrace;

        public ErrorResponseBuilder(HttpStatus status, String message) {
            this.timestamp = new Date();
            this.code = status.value();
            this.status = status.name();
            this.message = message;
        }
        public ErrorResponseBuilder data(Object data){
            this.data = data;
            return this;
        }
        public ErrorResponseBuilder stacktrace(String stacktrace){
            this.stacktrace = stacktrace;
            return this;
        }
        public ErrorResponse build() {
            return new ErrorResponse(this);
        }

    }
}
