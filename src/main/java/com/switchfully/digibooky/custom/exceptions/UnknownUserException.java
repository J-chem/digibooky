package com.switchfully.digibooky.custom.exceptions;

public class UnknownUserException extends RuntimeException{
    public UnknownUserException() {
        this("Username not found");
    }

    public UnknownUserException(String message) {
        super(message);
    }
}
