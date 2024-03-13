package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.mapper.UserMapper;
import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.model.UserGet;
import de.fi105.nachweiseBackend.repository.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationService {

    private final PersonRepository personRepository;
    private final UserMapper userMapper;

    public AuthorizationService(PersonRepository personRepository, UserMapper userMapper) {
        this.personRepository = personRepository;
        this.userMapper = userMapper;
    }

    public void checkRole(int userId, UserCreate.RoleEnum role) {
        if (!hasRole(userId, role)) {
            throw new RuntimeException();
        }
    }

    public boolean hasRole(int userId, UserCreate.RoleEnum role) {
        int actual = personRepository.getReferenceById(userId).getRole();
        return UserMapper.roleToInt(role) == actual;
    }

    public UserGet authenticate(String username, String password) {
        String hash = UserMapper.passwordToHash(password);
        var result = personRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);
        if (!result.getPasswordHash().equals(hash)) {
            throw new RuntimeException();
        }
        return userMapper.toUserGet(result);
    }

}
