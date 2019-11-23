package dev.aatwi.fmtservices.controller;

import dev.aatwi.fmtservices.dto.UserDTO;
import dev.aatwi.fmtservices.mapper.UserMapper;
import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.model.UserBuilder;
import dev.aatwi.fmtservices.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController
{
    @Autowired
    private LoginService loginService;


    @GetMapping(value = "/")
    public ResponseEntity<String> home()
    {
        return new ResponseEntity<>("Login Page", HttpStatus.OK);
    }


    @GetMapping("/{email}/{password}")
    public ResponseEntity<UserDTO> login(@PathVariable("email") String email, @PathVariable("password") String password)
    {
        User loggedInUser = loginService.login(email, password);
        if (loggedInUser.isNull())
        {
            return new ResponseEntity<>(UserMapper.toUserDTO(UserBuilder.newNullUser()), HttpStatus.NOT_FOUND);

        }
        else
        {
            return new ResponseEntity<>(UserMapper.toUserDTO(loggedInUser), HttpStatus.OK);
        }
    }
}
