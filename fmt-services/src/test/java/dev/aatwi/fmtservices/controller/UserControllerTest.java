package dev.aatwi.fmtservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.aatwi.fmtservices.FmtServicesApplication;
import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.model.UserBuilder;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FmtServicesApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserControllerTest {
    public static final String USER_CREATION_URL = "/api/users/create/";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void
    it_should_add_a_newUser_to_the_repository() throws Exception {
        User createdUser = UserBuilder.newUserBuilder()
                .withEmail("email@email.com")
                .withName("User Name")
                .withPassword("testPassword")
                .build();

        ResultActions createUserResult = performCreateUserAPICall(createdUser);
        assertUserCreationSucceeded(createdUser, createUserResult);
    }

    @Test
    public void
    it_should_throw_an_exception_when_creating_two_accounts_with_same_email() throws Exception {
        User userA = UserBuilder.newUserBuilder()
                .withEmail("emailA@email.com")
                .withName("UserA Name")
                .withPassword("testPasswordA")
                .build();

        User userB = UserBuilder.newUserBuilder()
                .withEmail("emailA@email.com")
                .withName("UserB Name")
                .withPassword("testPasswordB")
                .build();

        ResultActions userACreationResult = performCreateUserAPICall(userA);
        assertUserCreationSucceeded(userA, userACreationResult);

        Assertions.assertThrows(Exception.class, () -> performCreateUserAPICall(userB));
    }

    private ResultActions performCreateUserAPICall(User createdUser) throws Exception {
        String request = "?email=" + createdUser.getEmail() +
                "&name=" + createdUser.getName() +
                "&password=" + createdUser.getPassword();
        ObjectMapper objectMapper = new ObjectMapper();

        return mockMvc.perform(post(USER_CREATION_URL + request)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createdUser)));
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
        User bob = UserBuilder.newUserBuilder()
                .withEmail("bob@email.com")
                .withName("Bob")
                .withPassword("BobPassword")
                .build();

        User john = UserBuilder.newUserBuilder()
                .withEmail("john@email.com")
                .withName("John")
                .withPassword("JohnPassword")
                .build();

        userRepository.saveAndFlush(bob);
        userRepository.saveAndFlush(john);

        mockMvc.perform(get("/api/users/all/")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email", is(bob.getEmail())))
                .andExpect(jsonPath("$[1].email", is(john.getEmail())));
    }

    @AfterEach
    public void
    resetDB() {
        userRepository.deleteAll();
    }
}
