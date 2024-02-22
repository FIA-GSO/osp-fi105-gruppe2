package de.fi105.nachweiseBackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.fi105.nachweiseBackend.api.SessionApiDelegate;
import de.fi105.nachweiseBackend.model.UserGet;
import de.fi105.nachweiseBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class SessionController implements SessionApiDelegate {

    private final Algorithm algorithm;
    private final UserService userService;

    public SessionController(Algorithm algorithm, UserService userService) {
        this.algorithm = algorithm;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> getSession(String authorization) {
        String[] authFields = authorization.split(" ");
        if (!authFields[0].equals("Basic")) {
            throw new RuntimeException();
        }
        String decoded = new String(Base64.getDecoder().decode(authFields[1]));
        String[] credentials = decoded.split(":");

        UserGet user = userService.authenticate(credentials[0], credentials[1]);

        String jwtToken = JWT.create()
                .withIssuer("nachweiseBackend")
                .withSubject("session")
                .withClaim("userId", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000L))
                .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                .sign(algorithm);

        return ResponseEntity.ok().header("Set-Cookie", "JSESSIONID=" + jwtToken).build();
    }

}
