package de.fi105.nachweiseBackend.controller;

import de.fi105.nachweiseBackend.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    @ExceptionHandler({ServiceException.class})
    protected ResponseEntity<Object> resolveException(ServiceException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getContent());
    }

}
