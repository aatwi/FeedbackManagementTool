package dev.aatwi.fmtservices.controller;

import dev.aatwi.fmtservices.dto.UserDTO;
import dev.aatwi.fmtservices.mapper.UserMapper;
import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static dev.aatwi.fmtservices.mapper.UserMapper.toUserDTO;

@RestController
@RequestMapping("api/users")
public class UserController
{
    @Autowired
    private UserService userService;


    @GetMapping(value = "/")
    public String home()
    {
        return "User Management Page";
    }


    @PostMapping(value = "/create/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
    {
        try
        {
            User createdUser = userService.saveUser(UserMapper.toUser(userDTO));
            return new ResponseEntity<>(toUserDTO(createdUser), HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/all/")
    public List<UserDTO> getAllUsers()
    {
        List<User> allUsers = userService.getAllUsers();
        return allUsers.stream().map(UserMapper::toUserDTO).collect(Collectors.toList());
    }
}
