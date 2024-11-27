package com.publib.app.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class BookNotFoundException extends LibraryException {
    public BookNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, Map.of("error", "Book not found"));
    }
}