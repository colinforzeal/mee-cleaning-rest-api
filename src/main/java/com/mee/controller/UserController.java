package com.mee.controller;

import com.mee.entity.User;
import com.mee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/api/users")
    public User findAll(Principal principal) {
        return userRepository.findByFirstName("hello");
    }
}
