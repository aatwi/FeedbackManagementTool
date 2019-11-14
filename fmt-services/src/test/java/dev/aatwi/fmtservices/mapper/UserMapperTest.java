package dev.aatwi.fmtservices.mapper;

import dev.aatwi.fmtservices.model.User;
import org.junit.jupiter.api.Test;

import static dev.aatwi.fmtservices.dto.UserDTOBuilder.newUserDTOBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest
{

    @Test
    public void
    it_should_convert_a_UserDTO_class_to_a_User_object()
    {
        assertEquals(
            new User("email", "name", "Pass"),

            UserMapper.toUser(newUserDTOBuilder()
                .withEmail("email")
                .withName("name")
                .withPassword("Pass")
                .build())
        );
    }


    @Test
    public void
    it_should_convert_a_User_object_to_a_UserDTO_object()
    {
        assertEquals(
            UserMapper.toUserDTO(new User("email", "name", "Pass")),

            newUserDTOBuilder()
                .withEmail("email")
                .withName("name")
                .withPassword("Pass")
                .build()
        );

    }
}
