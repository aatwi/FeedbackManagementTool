package dev.aatwi.fmtservices.services;

import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService
{
    @Autowired
    private UserRepository userRepository;


    @Override
    public User login(String email, String password)
    {
        Optional<User> loggedInUser = userRepository.findAll().stream().filter(
            user -> authenticateUser(user, email, password)).findFirst();
        return loggedInUser.orElseGet(User::new);
    }


    private boolean authenticateUser(User user, String email, String password)
    {
        return user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password);
    }
}
