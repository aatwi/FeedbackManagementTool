package dev.aatwi.fmt.services.account.management.services;

import dev.aatwi.fmt.services.account.management.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User newUser);

    List<User> getAllUsers();
}
