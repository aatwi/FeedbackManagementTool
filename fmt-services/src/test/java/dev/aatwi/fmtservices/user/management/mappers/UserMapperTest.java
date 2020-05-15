package dev.aatwi.fmtservices.user.management.mappers;

import org.junit.jupiter.api.Test;

import static dev.aatwi.fmtservices.user.management.dto.UserDTOBuilder.newUserDTOBuilder;
import static dev.aatwi.fmtservices.user.management.entities.UserBuilder.newUserBuilder;
import static dev.aatwi.fmtservices.user.management.mappers.UserMapper.convertUserDTOtoUser;
import static dev.aatwi.fmtservices.user.management.mappers.UserMapper.convertUserToUserDTO;
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
