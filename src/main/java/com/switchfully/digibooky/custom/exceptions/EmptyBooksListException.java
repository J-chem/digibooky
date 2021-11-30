package com.switchfully.digibooky.custom.exceptions;

public class EmptyBooksListException extends RuntimeException {
    public EmptyBooksListException(String message) {
        super(message);
    }
}
