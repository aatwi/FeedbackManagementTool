package dev.aatwi.fmtservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.aatwi.fmtservices.FmtServicesApplication;
import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.model.UserBuilder;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FmtServicesApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserControllerTest
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void
    it_should_add_a_newUser_to_the_repository() throws Exception
    {
        User createdUser = UserBuilder.newUserBuilder()
            .withEmail("email@email.com")
            .withName("User Name")
            .withPassword("testPassword")
            .build();

        userRepository.save(createdUser);
        userRepository.flush();

        ObjectMapper objectMapper = new ObjectMapper();
        String request = "?" + "email=email@email.com" + "&" + "name=User Name" + "&" + "password=testPassword";
        mockMvc.perform(
            post("/api/users/create/" + request)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(createdUser)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.email", is(createdUser.getEmail())))
            .andExpect(jsonPath("$.name", is(createdUser.getName())))
            .andExpect(jsonPath("$.password", is(createdUser.getPassword())));
    }


    @Test
    public void
    it_should_return_a_list_of_all_users_in_the_repository() throws Exception
    {
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
    resetDB()
    {
        userRepository.deleteAll();
    }
}
