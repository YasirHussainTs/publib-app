package com.publib.app.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class PatronNotFoundException extends LibraryException {
    public PatronNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, Map.of("error", "Patron not found"));
    }
}