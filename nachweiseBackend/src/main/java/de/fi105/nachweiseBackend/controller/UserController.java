package de.fi105.nachweiseBackend.controller;

import de.fi105.nachweiseBackend.api.UserApiDelegate;
import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.model.UserGet;
import de.fi105.nachweiseBackend.model.UserPatch;
import de.fi105.nachweiseBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserController implements UserApiDelegate {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserGet> postUser(UserCreate userCreate) {
        return ResponseEntity.ok(userService.postUser(userCreate));
    }

    @Override
    public ResponseEntity<UserGet> patchUser(Integer id, UserPatch userPatch) {
        return ResponseEntity.ok(userService.patchUser(userPatch, id));
    }

    @Override
    public ResponseEntity<UserGet> getUser(Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
