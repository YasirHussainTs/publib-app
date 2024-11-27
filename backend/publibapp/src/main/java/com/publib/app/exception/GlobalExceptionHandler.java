package com.publib.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LibraryException.class)
    public ResponseEntity<Map<String, Object>> handleLibraryException(LibraryException ex) {
        Map<String, Object> errorResponse = Map.of(
                "message", ex.getMessage(),
                "status", ex.getStatusCode().value(),
                "details", ex.getErrorDetails()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        Map<String, Object> errorResponse = Map.of(
                "message", "An unexpected error occurred.",
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "details", Map.of("error", ex.getMessage())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}