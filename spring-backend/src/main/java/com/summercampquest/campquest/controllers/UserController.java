package com.summercampquest.campquest.controllers;

import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.User;
import com.summercampquest.campquest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public User loginUser(@RequestBody User user){

        String tempEmail = user.getEmail();
        String password = user.getPassword();
        User userObj = null;

        if(tempEmail.isBlank()|| password.isBlank()||tempEmail == null || password == null){
            try {
                throw new Exception("Bad Credentials");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }       //if email password blank
        else{
            userObj = userService.findUserByEmailAndPassword(tempEmail,password);
        }

         return userObj;


    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        if(userService.findUserExists(user.getUsername())||userService.findEmailExists(user.getEmail())){
        if (userService.findUserExists(user.getUsername())) {
            try {
                throw new Exception("Username taken");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
//            return ResponseEntity.badRequest().build();
        }      // if username exist in database

        if (userService.findEmailExists(user.getEmail())) {
            try {
                throw new Exception("Email address already registered");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //return ResponseEntity.badRequest().build();
        }       // if email already used
        } //If user and email exist in db

        userService.addUser(user);
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);

    }       // registerUser method
}
