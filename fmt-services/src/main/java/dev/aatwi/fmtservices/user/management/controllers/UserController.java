package dev.aatwi.fmtservices.user.management.controllers;

import dev.aatwi.fmtservices.user.management.dto.UserDTO;
import dev.aatwi.fmtservices.user.management.entities.User;
import dev.aatwi.fmtservices.user.management.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static dev.aatwi.fmtservices.user.management.mappers.UserMapper.convertUserDTOtoUser;
import static dev.aatwi.fmtservices.user.management.mappers.UserMapper.convertUserToUserDTO;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String home() {
        return "User Management Page";
    }

    @PostMapping(value = "/create/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            User createdUser = userService.saveUser(convertUserDTOtoUser(userDTO));
            return new ResponseEntity<>(convertUserToUserDTO(createdUser), HttpStatus.CREATED);
        } catch (RestClientResponseException exception) {
            throw new ResponseStatusException(CONFLICT, "User Already Exists in the System!", exception);
        }
    }

    @GetMapping(value = "/all/")
    public List<UserDTO> getAllUsers() {
        throw new UnsupportedOperationException();
    }
}
