package dev.aatwi.fmtservices.user.management.services;

import dev.aatwi.fmtservices.user.management.entities.User;

public interface LoginService {
    User login(String email, String password);
}
