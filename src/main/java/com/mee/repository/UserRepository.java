package com.mee.repository;

import com.mee.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    public User findByFirstName(String firstName);
}
