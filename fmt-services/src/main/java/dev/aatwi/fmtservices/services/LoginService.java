package dev.aatwi.fmtservices.services;

import dev.aatwi.fmtservices.model.User;

public interface LoginService
{
    User login(String email, String password);
}
