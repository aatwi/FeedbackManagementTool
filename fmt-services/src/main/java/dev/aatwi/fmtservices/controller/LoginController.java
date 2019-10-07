package dev.aatwi.fmtservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class LoginController
{
    @GetMapping(value = "login")
    public boolean login()
    {
        return false;
    }
}
