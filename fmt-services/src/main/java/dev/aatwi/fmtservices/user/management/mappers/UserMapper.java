package dev.aatwi.fmtservices.user.management.mappers;

import dev.aatwi.fmtservices.user.management.dto.UserDTO;
import dev.aatwi.fmtservices.user.management.entities.User;

import static dev.aatwi.fmtservices.user.management.dto.UserDTOBuilder.newUserDTOBuilder;

public final class UserMapper {
    private UserMapper() {
    }

    public static User convertUserDTOtoUser(UserDTO userDTO) {
        return new User(userDTO.getEmail(), userDTO.getName(), userDTO.getPassword());
    }

    public static UserDTO convertUserToUserDTO(User user) {
        return newUserDTOBuilder()
                .withEmail(user.getEmail())
                .withName(user.getName())
                .withPassword(user.getPassword())
                .build();
    }
}
