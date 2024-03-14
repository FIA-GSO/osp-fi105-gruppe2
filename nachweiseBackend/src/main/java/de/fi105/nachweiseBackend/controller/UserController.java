package de.fi105.nachweiseBackend.controller;

import de.fi105.nachweiseBackend.api.UserApiDelegate;
import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.model.UserGet;
import de.fi105.nachweiseBackend.model.UserPatch;
import de.fi105.nachweiseBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserController extends BaseController implements UserApiDelegate {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserGet> postUser(UserCreate userCreate) {
        return ResponseEntity.ok(userService.postUser(userCreate));
    }

    @Override
    public ResponseEntity<UserGet> patchUser(String userName, UserPatch userPatch) {
        var user = userService.getUser(userName);
        return ResponseEntity.ok(userService.patchUser(userPatch, user.getId()));
    }

    @Override
    public ResponseEntity<UserGet> getUser(String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

}
