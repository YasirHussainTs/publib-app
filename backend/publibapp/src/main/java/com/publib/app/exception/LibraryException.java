package com.publib.app.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class LibraryException extends RuntimeException {
    private final HttpStatus statusCode;
    private final Map<String, String> errorDetails;

    public LibraryException(String message, HttpStatus statusCode, Map<String, String> errorDetails) {
        super(message);
        this.statusCode = statusCode;
        this.errorDetails = errorDetails;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getErrorDetails() {
        return errorDetails;
    }
}