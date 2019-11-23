package dev.aatwi.fmtservices.controller;

import dev.aatwi.fmtservices.model.User;
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
    public ResponseEntity<User> login(@PathVariable("email") String email, @PathVariable("password") String password)
    {
        final ResponseEntity<User> emptyResponse = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        User loggedInUser = loginService.login(email, password);
        if (loggedInUser.isNull())
        {
            return emptyResponse;
        }
        else
        {
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        }
    }
}
