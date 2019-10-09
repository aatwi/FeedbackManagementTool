package dev.aatwi.fmtservices.controller;

import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
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
    public void createUser(@RequestBody User user)
    {
        userRepository.save(user);
    }


    @GetMapping(value = "/all/")
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
}
