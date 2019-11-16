package dev.aatwi.fmtservices.controller;

import dev.aatwi.fmtservices.dto.UserDTO;
import dev.aatwi.fmtservices.dto.UserDTOBuilder;
import dev.aatwi.fmtservices.mapper.UserMapper;
import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.model.UserBuilder;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest
{
    @InjectMocks
    UserController userController;

    @Mock
    UserRepository userRepository;

    private MockHttpServletRequest request;


    @BeforeEach
    void setUp()
    {
        request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }


    @Test
    public void itShouldAddANewUserToTheUserRepository()
    {
        User createdUser = UserBuilder.newUserBuilder()
            .withEmail("email@email.com")
            .withName("User Name")
            .withPassword("testPassword")
            .build();
        when(userRepository.save(any(User.class))).thenReturn(createdUser);

        UserDTO userDTO = UserDTOBuilder.newUserDTOBuilder()
            .withEmail("email@email.com")
            .withName("User Name")
            .withPassword("testPassword")
            .build();

        ResponseEntity responseEntity = userController.createUser(userDTO);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(UserMapper.toUserDTO(createdUser), responseEntity.getBody());
    }

}
