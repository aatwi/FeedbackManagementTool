package dev.aatwi.fmtservices.mapper;

import dev.aatwi.fmtservices.dto.UserDTO;
import dev.aatwi.fmtservices.model.User;

import static dev.aatwi.fmtservices.dto.UserDTOBuilder.newUserDTOBuilder;

public class UserMapper
{
    public static User convertUserDTOtoUser(UserDTO userDTO)
    {
        return new User(userDTO.getEmail(), userDTO.getName(), userDTO.getPassword());
    }


    public static UserDTO convertUserToUserDTO(User user)
    {
        return newUserDTOBuilder()
            .withEmail(user.getEmail())
            .withName(user.getName())
            .withPassword(user.getPassword())
            .build();
    }
}
