package dev.aatwi.fmt.services.account.management.mappers;

import dev.aatwi.fmt.services.account.management.dto.UserDTO;
import dev.aatwi.fmt.services.account.management.models.User;

import static dev.aatwi.fmt.services.account.management.dto.UserDTOBuilder.newUserDTOBuilder;

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
