package dev.aatwi.fmt.services.user.management.services;

import dev.aatwi.fmt.services.user.management.entities.User;

public interface LoginService {
    User login(String email, String password);
}
