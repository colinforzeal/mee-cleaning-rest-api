package com.mee.controller;

import com.mee.entity.User;
import com.mee.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/findByFirstName")
    public ResponseEntity<User> findAll(@RequestParam("firstName") String firstName) {
        logger.info("Find user by name = {}", firstName);
        return new ResponseEntity<>(userService.findByName(firstName), HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)//todo: only user function
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        logger.info("Find user with id = {}", id);
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)//todo: only for admin
    public ResponseEntity<List<User>> findAll() {
        logger.info("Find all user");
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}
