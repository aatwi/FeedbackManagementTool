package dev.aatwi.fmt.services.account.management.services;

import dev.aatwi.fmt.services.account.management.models.User;

public interface LoginService {
    User login(String email, String password);
}
