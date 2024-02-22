package de.fi105.nachweiseBackend.controller;

import de.fi105.nachweiseBackend.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    protected ResponseEntity<Object> resolveException(ServiceException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getContent());
    }

}
