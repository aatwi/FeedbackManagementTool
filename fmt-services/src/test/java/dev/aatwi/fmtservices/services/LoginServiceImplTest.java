package dev.aatwi.fmtservices.services;

import dev.aatwi.fmtservices.FmtServicesApplication;
import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.model.UserBuilder;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FmtServicesApplication.class)
public class LoginServiceImplTest
{

    @Autowired
    private LoginService loginService;
    @MockBean
    private UserRepository userRepository;


    @Test
    public void
    it_should_return_the_logged_in_user_in_case_of_successful_login()
    {
        User userTwo = UserBuilder.newUserBuilder()
            .withEmail("email2@email.com")
            .withName("User Two")
            .withPassword("UserTwoPassword")
            .build();

        when(userRepository.findUserByEmailAndPassword("email2@email.com", "UserTwoPassword")).thenReturn(userTwo);

        User loggedInUser = loginService.login("email2@email.com", "UserTwoPassword");

        assertEquals(userTwo, loggedInUser);
    }


    @Test
    public void
    it_should_return_an_empty_user_when_login_fails()
    {
        User userOne = UserBuilder.newUserBuilder()
            .withEmail("email1@email.com")
            .withName("User One")
            .withPassword("UserOnePassword")
            .build();

        when(userRepository.findUserByEmailAndPassword("email2@email.com", "UserTwoPassword")).thenReturn(null);

        User loggedInUser = loginService.login("email2@email.com", "UserTwoPassword");

        assertEquals(UserBuilder.newNullUser(), loggedInUser);
    }
}
