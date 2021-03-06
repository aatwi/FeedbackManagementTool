package dev.aatwi.fmt.services.account.management.repository;

import com.google.common.collect.Lists;
import dev.aatwi.fmt.services.FmtServicesApplication;
import dev.aatwi.fmt.services.account.management.models.User;
import dev.aatwi.fmt.services.account.management.models.UserBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import static dev.aatwi.fmt.services.account.management.models.UserBuilder.newUserBuilder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = FmtServicesApplication.class)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    User USER_A = newUserBuilder()
            .withEmail("UserA@email.com")
            .withName("User A")
            .withPassword("UserAPassword")
            .build();

    User USER_B = newUserBuilder()
            .withEmail("UserB@email.com")
            .withName("User B")
            .withPassword("UserBPassword")
            .build();

    @Test
    public void
    it_should_return_the_corresponding_user_when_searching_by_email_and_password() {
        assertEquals(
                USER_A,
                userRepository.findUserByEmailAndPassword("UserA@email.com", "UserAPassword"));
    }

    @Test
    public void
    it_should_return_the_corresponding_user_when_searching_by_email_and_password_regardless_of_case() {
        assertEquals(
                USER_A,
                userRepository.findUserByEmailAndPassword("USERA@email.com", "UserAPassword"));
    }

    @Test
    public void
    it_should_return_null_if_user_is_not_in_the_repository() {
        assertNull(userRepository.findUserByEmailAndPassword("UserC@email.com", "UserCPassword"));
    }

    @Test
    public void
    it_should_return_all_the_users_when_getAllUsers_is_called() {
        assertEquals(Lists.newArrayList(USER_A, USER_B), userRepository.findAll());
    }

    @Test
    public void
    it_should_throw_an_exception_when_saving_a_user_with_null_name() {
        assertTransactionSystemExceptionThrown(newUserBuilder()
                        .withEmail("email@email.com")
                        .withPassword("Password"),
                "User name can not be null");
    }

    @Test
    public void
    it_should_throw_an_exception_when_saving_a_user_with_null_email() {
        assertTransactionSystemExceptionThrown(newUserBuilder()
                        .withName("Name")
                        .withPassword("Password"),
                "Email can not be null");
    }

    @Test
    public void
    it_should_throw_an_exception_when_saving_a_user_with_null_password() {
        assertTransactionSystemExceptionThrown(newUserBuilder()
                        .withName("Name")
                        .withEmail("email@email.com"),
                "Password can not be null");
    }

    private void assertTransactionSystemExceptionThrown(UserBuilder user, String message) {
        TransactionSystemException transactionSystemException = assertThrows(TransactionSystemException.class,
                () -> userRepository.save(user.build()),
                "Exception not thrown");

        assertTrue(transactionSystemException.getRootCause().getMessage().contains(message));
    }

    @BeforeEach
    public void
    setup() {
        userRepository.save(USER_A);
        userRepository.save(USER_B);
    }

    @AfterEach
    public void
    deleteRepository() {
        userRepository.deleteAll();
    }
}
