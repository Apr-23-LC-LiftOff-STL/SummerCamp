package com.summercampquest.campquest.controllers;


import com.summercampquest.campquest.models.User;
import com.summercampquest.campquest.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;


@Service
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

}
