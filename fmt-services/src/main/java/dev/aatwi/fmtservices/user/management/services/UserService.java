package dev.aatwi.fmtservices.user.management.services;

import dev.aatwi.fmtservices.user.management.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User newUser);

    List<User> getAllUsers();
}
