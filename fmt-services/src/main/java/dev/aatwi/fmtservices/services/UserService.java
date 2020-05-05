package dev.aatwi.fmtservices.services;

import dev.aatwi.fmtservices.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User newUser);

    List<User> getAllUsers();
}
