package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.dto.User;
import de.fi105.nachweiseBackend.dto.entity.PersonEntity;
import de.fi105.nachweiseBackend.mapper.UserMapper;
import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.model.UserGet;
import de.fi105.nachweiseBackend.repository.PersonRepository;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserService {

    private final PersonRepository personRepository;
    private final UserMapper userMapper;

    public UserService(PersonRepository personRepository, UserMapper userMapper) {
        this.personRepository = personRepository;
        this.userMapper = userMapper;
    }

    public UserGet authenticate(String username, String password) {
        String hash = DigestUtils.sha256Hex(password);
        List<PersonEntity> result = personRepository.findAllByUsername(username);
        if (result.size() != 1) {
            throw new RuntimeException();
        }

        PersonEntity entity = result.get(0);
        if (!entity.getPasswordHash().equals(hash)) {
            throw new RuntimeException();
        }
        return userMapper.toUserGet(entity);
    }

    @Transactional
    public UserGet postUser(UserCreate userCreate) {
        PersonEntity save = personRepository.save(userMapper.fromCreate(userCreate));
        return userMapper.toUserGet(save);
    }

}
