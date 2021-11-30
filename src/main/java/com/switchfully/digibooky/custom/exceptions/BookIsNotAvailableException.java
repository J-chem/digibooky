package com.switchfully.digibooky.custom.exceptions;

public class BookIsNotAvailableException extends RuntimeException {
    public BookIsNotAvailableException(String message) {
        super(message);
    }
}
