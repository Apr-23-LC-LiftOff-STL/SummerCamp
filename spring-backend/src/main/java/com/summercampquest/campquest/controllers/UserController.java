package com.summercampquest.campquest.controllers;

import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.User;
import com.summercampquest.campquest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){

        if(userService.findUserExists(user.getUsername())){
            return ResponseEntity.badRequest().build();
        }      // if username exist in database

        if(userService.findEmailExists(user.getEmail())){
            return ResponseEntity.badRequest().build();
        }       // if email already used

        userService.addUser(user);
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);

    }       // registerUser method
}
