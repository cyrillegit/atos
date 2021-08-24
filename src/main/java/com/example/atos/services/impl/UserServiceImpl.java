package com.example.atos.services.impl;

import com.example.atos.exceptions.AtosException;
import com.example.atos.models.User;
import com.example.atos.repositories.UserRepository;
import com.example.atos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) throws AtosException {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()){
            throw new AtosException("User already in the database");
        }
        return userRepository.save(user);
    }

    @Override
    public User display(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
