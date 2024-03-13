package de.fi105.nachweiseBackend.controller;

import com.auth0.jwt.interfaces.JWTVerifier;
import de.fi105.nachweiseBackend.api.UserApiDelegate;
import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.model.UserGet;
import de.fi105.nachweiseBackend.model.UserPatch;
import de.fi105.nachweiseBackend.service.AuthorizationService;
import de.fi105.nachweiseBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static de.fi105.nachweiseBackend.model.UserCreate.RoleEnum.ADMIN;

@Component
public class UserController extends LoggedInController implements UserApiDelegate {

    private final UserService userService;

    public UserController(UserService userService, JWTVerifier jwtVerifier, AuthorizationService authorizationService) {
        super(jwtVerifier, authorizationService);
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserGet> postUser(UserCreate userCreate, String JSESSIONID) {
        authorizationService.checkRole(verifyUser(JSESSIONID), ADMIN);
        return ResponseEntity.ok(userService.postUser(userCreate));
    }

    @Override
    public ResponseEntity<UserGet> patchUser(UserPatch userPatch, String JSESSIONID) {
        int userId = verifyUser(JSESSIONID);
        return ResponseEntity.ok(userService.patchUser(userPatch, userId));
    }

    @Override
    public ResponseEntity<UserGet> getUser(String userName, String JSESSIONID) {
        return ResponseEntity.ok(userService.getUser(userName));
    }

}
