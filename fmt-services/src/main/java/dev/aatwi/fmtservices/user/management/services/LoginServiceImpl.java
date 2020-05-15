package dev.aatwi.fmtservices.user.management.services;

import dev.aatwi.fmtservices.repository.UserRepository;
import dev.aatwi.fmtservices.user.management.entities.User;
import dev.aatwi.fmtservices.user.management.entities.UserBuilder;
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
