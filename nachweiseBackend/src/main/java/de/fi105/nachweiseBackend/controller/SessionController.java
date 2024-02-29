package de.fi105.nachweiseBackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.fi105.nachweiseBackend.api.SessionApiDelegate;
import de.fi105.nachweiseBackend.model.Password;
import de.fi105.nachweiseBackend.model.UserGet;
import de.fi105.nachweiseBackend.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@Component
public class SessionController extends BaseController implements SessionApiDelegate {

    private final Algorithm algorithm;
    private final UserService userService;

    public SessionController(Algorithm algorithm, UserService userService) {
        this.algorithm = algorithm;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> getSession(String username, Password password) {
        UserGet user = userService.authenticate(username, password.getPassword());

        String jwtToken = JWT.create()
                .withIssuer("nachweiseBackend")
                .withSubject("session")
                .withClaim("userId", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000L))
                .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                .sign(algorithm);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie", "JSESSIONID=" + jwtToken);
        return ResponseEntity.noContent().headers(headers).build();
    }

}
