package dev.aatwi.fmt.services.user.management.services;

import dev.aatwi.fmt.services.repository.UserRepository;
import dev.aatwi.fmt.services.user.management.entities.User;
import dev.aatwi.fmt.services.user.management.entities.UserBuilder;
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