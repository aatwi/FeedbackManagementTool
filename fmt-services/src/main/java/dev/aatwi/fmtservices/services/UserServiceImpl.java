package dev.aatwi.fmtservices.services;

import dev.aatwi.fmtservices.model.User;
import dev.aatwi.fmtservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User saveUser(User newUser)
    {
        return userRepository.save(newUser);
    }


    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
}
