package dev.aatwi.fmt.services.user.management.services;

import dev.aatwi.fmt.services.FmtServicesApplication;
import dev.aatwi.fmt.services.user.management.entities.User;
import dev.aatwi.fmt.services.user.management.entities.UserBuilder;
import dev.aatwi.fmt.services.user.management.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FmtServicesApplication.class)
public class LoginServiceImplTest {

    @Autowired
    private LoginService loginService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void
    it_should_return_the_logged_in_user_in_case_of_successful_login() {
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
    it_should_return_an_empty_user_when_login_fails() {
        when(userRepository.findUserByEmailAndPassword("email2@email.com", "UserTwoPassword")).thenReturn(null);

        User loggedInUser = loginService.login("email2@email.com", "UserTwoPassword");

        assertEquals(UserBuilder.newNullUser(), loggedInUser);
    }
}
