package dev.aatwi.fmtservices.mapper;

import org.junit.jupiter.api.Test;

import static dev.aatwi.fmtservices.dto.UserDTOBuilder.newUserDTOBuilder;
import static dev.aatwi.fmtservices.mapper.UserMapper.convertUserDTOtoUser;
import static dev.aatwi.fmtservices.mapper.UserMapper.convertUserToUserDTO;
import static dev.aatwi.fmtservices.model.UserBuilder.newUserBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {
    @Test
    public void
    it_should_convert_a_UserDTO_class_to_a_User_object() {
        assertEquals(
                newUserBuilder()
                        .withEmail("email")
                        .withName("name")
                        .withPassword("Pass")
                        .build(),

                convertUserDTOtoUser(newUserDTOBuilder()
                        .withEmail("email")
                        .withName("name")
                        .withPassword("Pass")
                        .build())
        );
    }

    @Test
    public void
    it_should_convert_a_User_object_to_a_UserDTO_object() {
        assertEquals(
                convertUserToUserDTO(
                        newUserBuilder()
                                .withEmail("email")
                                .withName("name")
                                .withPassword("Pass")
                                .build()),

                newUserDTOBuilder()
                        .withEmail("email")
                        .withName("name")
                        .withPassword("Pass")
                        .build()
        );
    }
}
