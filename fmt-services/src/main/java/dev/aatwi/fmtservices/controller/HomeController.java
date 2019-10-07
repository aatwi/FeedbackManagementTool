package dev.aatwi.fmtservices.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController
{
    @RequestMapping("/")
    public String home()
    {
        return "Welcome to the 360 Feedback Management Tool";
    }
}
