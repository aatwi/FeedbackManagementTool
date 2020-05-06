package dev.aatwi.fmtservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.aatwi.fmtservices.dto.UserDTO;
import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.services.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestClientResponseException;

import static dev.aatwi.fmtservices.dto.UserDTOBuilder.newUserDTOBuilder;
import static dev.aatwi.fmtservices.mapper.UserMapper.convertUserDTOtoUser;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    public static final String USER_CREATION_URL = "/api/users/create/";

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void
    it_should_add_a_newUser_to_the_repository() throws Exception {
        UserDTO userDTOToCreate = newUserDTOBuilder()
                .withEmail("email@email.com")
                .withName("User Name")
                .withPassword("testPassword")
                .build();

        User createdUser = convertUserDTOtoUser(userDTOToCreate);
        createdUser.setUserId(123L);

        when(userService.saveUser(convertUserDTOtoUser(userDTOToCreate))).thenReturn(createdUser);

        ResultActions createUserResult = performCreateUserAPICall(userDTOToCreate);
        assertUserCreationSucceeded(createdUser, createUserResult);
    }

    @Test
    public void
    it_should_throw_an_exception_when_creating_two_accounts_with_same_email() throws Exception {
        UserDTO userDTOToCreate = newUserDTOBuilder()
                .withEmail("email@email.com")
                .withName("User Name")
                .withPassword("testPassword")
                .build();

        when(userService.saveUser(convertUserDTOtoUser(userDTOToCreate))).thenThrow(RestClientResponseException.class);

        ResultActions resultActions = performCreateUserAPICall(userDTOToCreate);
        resultActions.andExpect(status().isConflict());
    }

    private ResultActions performCreateUserAPICall(UserDTO userDTOToCreate) throws Exception {
        String request = "?email=" + userDTOToCreate.getEmail() +
                "&name=" + userDTOToCreate.getName() +
                "&password=" + userDTOToCreate.getPassword();
        ObjectMapper objectMapper = new ObjectMapper();

        return mockMvc.perform(post(USER_CREATION_URL + request)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTOToCreate)));
    }

    private void assertUserCreationSucceeded(User createdUser, ResultActions actions) throws Exception {
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(createdUser.getEmail())))
                .andExpect(jsonPath("$.name", is(createdUser.getName())))
                .andExpect(jsonPath("$.password", is(createdUser.getPassword())));
    }

    @Test
    @Disabled("To be implemented Later")
    public void
    it_should_return_a_list_of_all_users_in_the_repository() throws Exception {
    }
}
