package de.fi105.nachweiseBackend.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import de.fi105.nachweiseBackend.exception.ServiceException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    private final HttpServletRequest servletRequest;
    private final JWTVerifier jwtVerifier;

    public BaseController(HttpServletRequest servletRequest, JWTVerifier jwtVerifier) {
        this.servletRequest = servletRequest;
        this.jwtVerifier = jwtVerifier;
    }


    protected int getUserId() {
        Cookie[] cookies = servletRequest.getCookies();
        String token = null;
        for (var c : cookies) {
            if (c.getName().equals("JSESSIONID")) {
                token = c.getValue();
            }
        }
        DecodedJWT verified = jwtVerifier.verify(token);
    }

    @ExceptionHandler({ServiceException.class})
    protected ResponseEntity<Object> resolveException(ServiceException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getContent());
    }

}
