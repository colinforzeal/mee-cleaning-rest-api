package com.mee.service.user;

import com.mee.entity.User;

import java.util.List;

public interface UserService {
    User findByFirstName(String firstName);
    User findById(String id);
    List<User> findAll();
}
