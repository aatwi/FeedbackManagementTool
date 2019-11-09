package dev.aatwi.fmtservices.controller;

import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest
{
    @InjectMocks
    LoginController loginController;

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
    public void itShouldReturnNotFoundCodeIfUserIsNotFoundOrUserIsWrong()
    {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<User> responseEntity = loginController.login("email@email.com", "testPassword");
        assertEquals(404, responseEntity.getStatusCodeValue());
    }


    @Test
    public void itShouldReturnTheUserNotFoundCodeIfUserIsNotFoundOrUserIsWrong()
    {
        when(userRepository.findAll()).thenReturn(Lists.newArrayList(new User("email@email.com", "User Name", "testPassword")));

        ResponseEntity<User> responseEntity = loginController.login("email@email.com", "testPassword");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(new User("email@email.com", "User Name", "testPassword"), responseEntity.getBody());
    }
}
