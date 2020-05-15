package dev.aatwi.fmt.services.user.management.controllers;

import dev.aatwi.fmt.services.user.management.dto.UserDTO;
import dev.aatwi.fmt.services.user.management.mappers.UserMapper;
import dev.aatwi.fmt.services.user.management.models.User;
import dev.aatwi.fmt.services.user.management.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Login Page", HttpStatus.OK);
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<UserDTO> login(@PathVariable("email") String email, @PathVariable("password") String password) {
        User loggedInUser = loginService.login(email, password);
        HttpStatus responseStatus = loggedInUser.isNull() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(UserMapper.convertUserToUserDTO(loggedInUser), responseStatus);
    }
}
