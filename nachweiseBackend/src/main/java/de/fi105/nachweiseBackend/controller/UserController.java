package de.fi105.nachweiseBackend.controller;

import de.fi105.nachweiseBackend.api.UserApiDelegate;
import de.fi105.nachweiseBackend.mapper.UserMapper;
import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.model.UserGet;
import de.fi105.nachweiseBackend.repository.PersonRepository;
import de.fi105.nachweiseBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserController implements UserApiDelegate {

    private final PersonRepository repository;
    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(PersonRepository repository, UserMapper userMapper, UserService userService) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserGet> postUser(UserCreate userCreate) {
        return ResponseEntity.ok(userService.postUser(userCreate));
    }

    @Override
    public ResponseEntity<UserGet> getUser(Integer id) {
        return ResponseEntity.ok(userMapper.toUserGet(repository.getReferenceById(id)));
    }
}
