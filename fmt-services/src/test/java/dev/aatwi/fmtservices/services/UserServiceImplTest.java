package dev.aatwi.fmtservices.services;

import dev.aatwi.fmtservices.FmtServicesApplication;
import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.model.UserBuilder;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = FmtServicesApplication.class)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void
    it_should_add_a_newUser_to_the_repository() {
        User createdUser = UserBuilder.newUserBuilder()
                .withEmail("email@email.com")
                .withName("User Name")
                .withPassword("testPassword")
                .build();
        when(userRepository.save(any(User.class))).thenReturn(createdUser);

        assertEquals(createdUser, userService.saveUser(createdUser));
    }

    @Test
    public void
    it_should_get_all_users_from_repository() {
        User userOne = UserBuilder.newUserBuilder()
                .withEmail("email1@email.com")
                .withName("User One")
                .withPassword("UserOnePassword")
                .build();

        User userTwo = UserBuilder.newUserBuilder()
                .withEmail("email1@email.com")
                .withName("User One")
                .withPassword("UserOnePassword")
                .build();

        when(userRepository.findAll()).thenReturn(Lists.newArrayList(userOne, userTwo));

        assertEquals(Lists.newArrayList(userOne, userTwo), userService.getAllUsers());
    }
}
    
