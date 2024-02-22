package de.fi105.nachweiseBackend.service;

import de.fi105.nachweiseBackend.entity.PersonEntity;
import de.fi105.nachweiseBackend.mapper.UserMapper;
import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.model.UserGet;
import de.fi105.nachweiseBackend.model.UserPatch;
import de.fi105.nachweiseBackend.repository.PersonRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

import static de.fi105.nachweiseBackend.mapper.UserMapper.passwordToHash;

@Component
public class UserService {

    private final PersonRepository personRepository;
    private final UserMapper userMapper;

    public UserService(PersonRepository personRepository, UserMapper userMapper) {
        this.personRepository = personRepository;
        this.userMapper = userMapper;
    }

    public UserGet getUser(int id) {
        return userMapper.toUserGet(personRepository.getReferenceById(id));
    }

    @Transactional
    public UserGet patchUser(UserPatch userPatch, int id) {
        PersonEntity entity = personRepository.getReferenceById(id);
        if (userPatch.getEmail() != null) {
            entity.setEmail(userPatch.getEmail());
        }
        if (userPatch.getPassword() != null) {
            entity.setEmail(passwordToHash(userPatch.getEmail()));
        }
        return userMapper.toUserGet(personRepository.save(entity));
    }

    @Transactional
    public UserGet postUser(UserCreate userCreate) {
        char[] lastname = userCreate.getNachname().toCharArray();
        char[] prename = userCreate.getVorname().toCharArray();
        String username = "" + lastname[0] + lastname[1] + lastname[2] + prename[0] + prename[1] + prename[2];

        List<PersonEntity> allByUsername = personRepository.findAllByUsername(username);
        int i = 1;
        while (!allByUsername.isEmpty()) {
            username = username + i;
            allByUsername = personRepository.findAllByUsername(username);
            i++;
        }

        PersonEntity save = personRepository.save(userMapper.fromCreate(userCreate, username));
        return userMapper.toUserGet(save);
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

    @PostConstruct
    @Transactional
    public void init() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setUsername("root");
        personEntity.setPasswordHash(UserMapper.passwordToHash("root"));
        personEntity.setRole(1);
        personEntity.setEmail("asdf");
        personEntity.setLastname("user");
        personEntity.setPrename("root");
        personRepository.save(personEntity);
    }

}
