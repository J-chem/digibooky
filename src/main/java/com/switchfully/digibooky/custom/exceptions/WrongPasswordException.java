package com.switchfully.digibooky.custom.exceptions;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException() {this("You input the wrong password");}

    public WrongPasswordException(String message) {
        super(message);
    }
}
