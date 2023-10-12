package org.food.delivery.controller;



import org.food.delivery.entity.User;
import org.food.delivery.event.SMSService;
import org.food.delivery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    private Logger logger = LoggerFactory.getLogger(UserController.class);




    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> listUsers = userService.getAllUsersWithCardDetails();
        return new ResponseEntity<>(listUsers,HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User person = userService.createUserWithCard(user);
        logger.info("CREATED USER ", person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
}
