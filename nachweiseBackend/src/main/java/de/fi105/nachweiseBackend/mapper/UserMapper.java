package de.fi105.nachweiseBackend.mapper;

import de.fi105.nachweiseBackend.entity.PersonEntity;
import de.fi105.nachweiseBackend.model.UserCreate;
import de.fi105.nachweiseBackend.model.UserGet;
import org.apache.commons.codec.digest.DigestUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface UserMapper {

    @Mapping(target = "prename", source = "input.vorname")
    @Mapping(target = "lastname", source = "input.nachname")
    @Mapping(target = "passwordHash", source = "input.password", qualifiedByName = "passwordToHash")
    @Mapping(target = "role", source = "input.role", qualifiedByName = "roleToInt")
    @Mapping(target = "username", source = "username")
    PersonEntity fromCreate(UserCreate input, String username);

    @Mapping(target = "userName", source = "username")
    @Mapping(target = "vorname", source = "prename")
    @Mapping(target = "nachname", source = "lastname")
    @Mapping(target = "role", source = "role", qualifiedByName = "intToRole")
    UserGet toUserGet(PersonEntity entity);

    @Named("passwordToHash")
    static String passwordToHash(String input) {
        return DigestUtils.sha256Hex(input);
    }

    @Named("roleToInt")
    static int roleToInt(UserCreate.RoleEnum value) {
        switch (value) {
            case AZUBI -> {
                return 0;
            }
            case PR_FER_LEHRER -> {
                return 1;
            }
            case AUSBILDER -> {
                return 2;
            }
            default -> throw new RuntimeException();
        }
    }
    @Named("intToRole")
    static UserGet.RoleEnum intToRole(int value) {
        switch (value) {
            case 0 -> {
                return UserGet.RoleEnum.AZUBI;
            }
            case 1 -> {
                return UserGet.RoleEnum.PR_FER_LEHRER;
            }
            case 2 -> {
                return UserGet.RoleEnum.AUSBILDER;
            }
            default -> throw new RuntimeException();
        }
    }

}
