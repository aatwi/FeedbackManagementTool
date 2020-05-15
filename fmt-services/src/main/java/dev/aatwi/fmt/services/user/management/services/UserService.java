package dev.aatwi.fmt.services.user.management.services;

import dev.aatwi.fmt.services.user.management.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User newUser);

    List<User> getAllUsers();
}
