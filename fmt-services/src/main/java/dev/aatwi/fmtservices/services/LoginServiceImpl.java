package dev.aatwi.fmtservices.services;

import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.model.UserBuilder;
import dev.aatwi.fmtservices.repository.UserRepository;
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
