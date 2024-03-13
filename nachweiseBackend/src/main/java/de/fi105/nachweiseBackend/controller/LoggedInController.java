package de.fi105.nachweiseBackend.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import de.fi105.nachweiseBackend.exception.ServiceException;
import de.fi105.nachweiseBackend.service.AuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class LoggedInController {

    private final JWTVerifier jwtVerifier;
    protected final AuthorizationService authorizationService;

    public LoggedInController(JWTVerifier jwtVerifier, AuthorizationService authorizationService) {
        this.jwtVerifier = jwtVerifier;
        this.authorizationService = authorizationService;
    }

    protected int verifyUser(String JSESSIONID) {
        DecodedJWT verified = null;
        try {
            verified = jwtVerifier.verify(JSESSIONID);
        } catch (JWTVerificationException e) {
            throw new ServiceException(401, "unauthorized");
        }

        return verified.getClaim("userId").as(Integer.class);
    }

    @ExceptionHandler({ServiceException.class})
    protected ResponseEntity<Object> resolveException(ServiceException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getContent());
    }

}
