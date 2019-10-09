package dev.aatwi.fmtservices.controller;

import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/login")
public class LoginController
{
    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "/")
    public String home()
    {
        return "Login Page";
    }


    @GetMapping("/{email}/{password}")
    public User login(@PathVariable("email") String email, @PathVariable("password") String password)
    {
        Optional<User> first = userRepository.findAll().stream().filter(
            user -> authenticateUser(user, email, password)).findFirst();
        return first.get();
    }


    private boolean authenticateUser(User user, String email, String password)
    {
        return user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password);
    }
}
