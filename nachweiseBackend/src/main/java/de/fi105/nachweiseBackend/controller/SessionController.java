package de.fi105.nachweiseBackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.fi105.nachweiseBackend.api.SessionApiDelegate;
import de.fi105.nachweiseBackend.model.Password;
import de.fi105.nachweiseBackend.model.UserGet;
import de.fi105.nachweiseBackend.service.AuthorizationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SessionController implements SessionApiDelegate {

    private final Algorithm algorithm;
    private final AuthorizationService authorizationService;

    public SessionController(Algorithm algorithm, AuthorizationService authorizationService) {
        this.algorithm = algorithm;
        this.authorizationService = authorizationService;
    }

    @Override
    public ResponseEntity<String> getSession(String username, Password password) {
        UserGet user = authorizationService.authenticate(username, password.getPassword());

        String jwtToken = JWT.create()
                .withIssuer("nachweiseBackend")
                .withSubject("session")
                .withClaim("userId", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000L))
                .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                .sign(algorithm);

        return ResponseEntity.ok(jwtToken);
    }

}
