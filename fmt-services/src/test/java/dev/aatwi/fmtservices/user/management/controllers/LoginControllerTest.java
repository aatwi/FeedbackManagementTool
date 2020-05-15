package dev.aatwi.fmtservices.user.management.controllers;

import dev.aatwi.fmtservices.user.management.entities.User;
import dev.aatwi.fmtservices.user.management.entities.UserBuilder;
import dev.aatwi.fmtservices.user.management.services.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static dev.aatwi.fmtservices.user.management.entities.UserBuilder.newNullUser;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    public static final String USER_LOGIN_URL = "/api/login/";

    @MockBean
    private LoginService loginService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void
    it_should_return_NotFound_if_user_is_not_in_the_repository() throws Exception {
        when(loginService.login("email@email.com", "testPassword")).thenReturn(newNullUser());

        mockMvc.perform(get(USER_LOGIN_URL + "email@email.com/testPassword")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void
    it_should_return_NotFound_when_password_is_wrong() throws Exception {
        when(loginService.login("john@email.com", "JohnPassword")).thenReturn(newNullUser());

        mockMvc.perform(get(USER_LOGIN_URL + "john@email.com/JohnPassword")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void
    it_should_return_the_user_instance_when_user_is_found_in_the_repository() throws Exception {
        User loggedInUser = UserBuilder.newUserBuilder()
                .withEmail("bob@email.com")
                .withName("Bob Name")
                .withPassword("testPassword2")
                .build();

        when(loginService.login("bob@email.com", "testPassword2"))
                .thenReturn(loggedInUser);

        mockMvc.perform(get(USER_LOGIN_URL + "bob@email.com/testPassword2")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(loggedInUser.getName())))
                .andExpect(jsonPath("$.email", is(loggedInUser.getEmail())))
                .andExpect(jsonPath("$.password", is(loggedInUser.getPassword())));
    }
}
