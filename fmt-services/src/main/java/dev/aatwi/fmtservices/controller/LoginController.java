package dev.aatwi.fmtservices.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{
    @RequestMapping("/")
    public String login()
    {
        return "Welcome to the 360 Feedback Management Tool";
    }
}
