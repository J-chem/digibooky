package com.switchfully.digibooky.api;

import com.switchfully.digibooky.custom.exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

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
        response.sendError(FORBIDDEN.value(), ue.getMessage());
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

    @ExceptionHandler(NotUniqueException.class)
    protected void securityNumberAlreadyExists (NotUniqueException ssnrae,
                                                HttpServletResponse response) throws IOException{
        response.sendError(BAD_REQUEST.value(), ssnrae.getMessage());
    }

    @ExceptionHandler(BookIsNotAvailableException.class)
    protected void bookIsNotAvailable(BookIsNotAvailableException bina,
                                      HttpServletResponse response) throws IOException{
        response.sendError(NOT_FOUND.value(), bina.getMessage());
    }

}
