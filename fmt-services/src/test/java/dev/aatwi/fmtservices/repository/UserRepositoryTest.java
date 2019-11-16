package dev.aatwi.fmtservices.repository;

import com.google.common.collect.Lists;
import dev.aatwi.fmtservices.FmtServicesApplication;
import dev.aatwi.fmtservices.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static dev.aatwi.fmtservices.model.UserBuilder.newUserBuilder;

@SpringBootTest(classes = FmtServicesApplication.class)
public class UserRepositoryTest
{
    @Autowired
    UserRepository userRepository;


    @Test
    public void
    it_should_return_all_the_users_when_getAllUsers_is_called()
    {
        User userA = newUserBuilder()
            .withEmail("UserA@email.com")
            .withName("User A")
            .withPassword("UserAPassword")
            .build();

        User userB = newUserBuilder()
            .withEmail("UserB@email.com")
            .withName("User B")
            .withPassword("UserBPassword")
            .build();
        userRepository.save(userA);
        userRepository.save(userB);

        Assertions.assertEquals(Lists.newArrayList(userA, userB), userRepository.findAll());
    }


    @AfterEach
    public void
    tear_down()
    {
        userRepository.deleteAll();
    }
}
