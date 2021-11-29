package com.switchfully.digibooky.api;

import com.switchfully.digibooky.custom.exceptions.ObjectNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    protected void entityDoesNotExistInDb(ObjectNotFoundException onfe,
                                          HttpServletResponse response) throws IOException {
        response.sendError(NOT_FOUND.value(), onfe.getMessage());
    }


}
