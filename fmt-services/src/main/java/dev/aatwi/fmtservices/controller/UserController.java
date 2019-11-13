package dev.aatwi.fmtservices.controller;

import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController
{
    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "/")
    public String home()
    {
        return "User Management Page";
    }


    @PostMapping(value = "/create/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        try
        {
            User createdUser = userRepository.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/all/")
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
}
