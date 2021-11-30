package com.switchfully.digibooky.exceptions;

public class EmptyBooksListException extends RuntimeException {
    public EmptyBooksListException(String message) {
        super(message);
    }
}
