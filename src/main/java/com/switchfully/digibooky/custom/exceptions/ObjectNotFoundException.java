package com.switchfully.digibooky.custom.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {
        this("\nCannot find object");
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
