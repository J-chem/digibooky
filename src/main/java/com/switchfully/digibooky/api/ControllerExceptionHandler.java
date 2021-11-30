package com.switchfully.digibooky.api;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import com.switchfully.digibooky.custom.exceptions.UnauthorizedException;
import com.switchfully.digibooky.custom.exceptions.UnknownUserException;
import com.switchfully.digibooky.custom.exceptions.WrongPasswordException;
import com.switchfully.digibooky.exceptions.EmptyBooksListException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    protected void entityDoesNotExistInDb(ObjectNotFoundException onfe,
                                          HttpServletResponse response) throws IOException {
        response.sendError(NOT_FOUND.value(), onfe.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected void illegalArgumentException(IllegalArgumentException iae,
                                          HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), iae.getMessage());
    }

    @ExceptionHandler(EmptyBooksListException.class)
    protected void emptyBooksListException(EmptyBooksListException eble,
                                           HttpServletResponse response) throws  IOException {
        response.sendError(BAD_REQUEST.value(), eble.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected void userNotAuthenticated(UnauthorizedException ue,
                                        HttpServletResponse response) throws IOException {
        response.sendError(NOT_FOUND.value(), ue.getMessage());
    }

    @ExceptionHandler(UnknownUserException.class)
    protected void unknownUser(UnknownUserException uue,
                               HttpServletResponse response) throws IOException{
        response.sendError(NOT_FOUND.value(), uue.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    protected void wrongPassword (WrongPasswordException wpe,
                                  HttpServletResponse response) throws IOException{
        response.sendError(NOT_FOUND.value(), wpe.getMessage());
    }

}
