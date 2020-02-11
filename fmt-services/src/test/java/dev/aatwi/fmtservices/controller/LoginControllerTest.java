package dev.aatwi.fmtservices.controller;

import dev.aatwi.fmtservices.FmtServicesApplication;
import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static dev.aatwi.fmtservices.model.UserBuilder.newUserBuilder;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FmtServicesApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class LoginControllerTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void
    it_should_return_NotFound_if_user_is_not_in_the_repository() throws Exception {
        mockMvc.perform(get("/api/login/email@email.com/testPassword")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void
    it_should_return_NotFound_when_password_is_wrong() throws Exception {
        userRepository.save(newUserBuilder()
                .withEmail("john@email.com")
                .withName("John Name")
                .withPassword("JohnPassword")
                .build());
        mockMvc.perform(get("/api/login/john@email.com/testPassssword")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void
    it_should_return_the_user_instance_when_user_is_found_in_the_repository() throws Exception {
        User bob = newUserBuilder()
                .withEmail("bob@email.com")
                .withName("Bob Name")
                .withPassword("testPassword2")
                .build();

        userRepository.save(bob);

        mockMvc.perform(get("/api/login/bob@email.com/testPassword2")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(bob.getName())))
                .andExpect(jsonPath("$.email", is(bob.getEmail())))
                .andExpect(jsonPath("$.password", is(bob.getPassword())));
    }

    @AfterEach
    public void
    resetDB() {
        userRepository.deleteAll();
    }
}
