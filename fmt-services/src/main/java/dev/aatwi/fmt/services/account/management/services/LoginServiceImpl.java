package dev.aatwi.fmt.services.account.management.services;

import dev.aatwi.fmt.services.account.management.models.User;
import dev.aatwi.fmt.services.account.management.models.UserBuilder;
import dev.aatwi.fmt.services.account.management.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String email, String password) {
        User foundUser = userRepository.findUserByEmailAndPassword(email, password);
        return foundUser != null ? foundUser : UserBuilder.newNullUser();
    }
}
