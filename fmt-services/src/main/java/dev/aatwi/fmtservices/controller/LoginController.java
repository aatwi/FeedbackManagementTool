package dev.aatwi.fmtservices.controller;

import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/login")
public class LoginController
{
    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "/")
    public ResponseEntity<String> home()
    {
        return new ResponseEntity<>("Login Page", HttpStatus.OK);
    }


    @GetMapping("/{email}/{password}")
    public ResponseEntity<User> login(@PathVariable("email") String email, @PathVariable("password") String password)
    {
        final ResponseEntity<User> emptyResponse = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        Optional<User> loggedInUser = userRepository.findAll().stream().filter(
            user -> authenticateUser(user, email, password)).findFirst();
        return loggedInUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElse(emptyResponse);
    }


    private boolean authenticateUser(User user, String email, String password)
    {
        return user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password);
    }
}
