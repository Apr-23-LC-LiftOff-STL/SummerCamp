package com.summercampquest.campquest.controllers;

import com.summercampquest.campquest.exceptions.EmailExistsException;
import com.summercampquest.campquest.exceptions.ExceptionHandlingController;
import com.summercampquest.campquest.exceptions.UserNotFoundException;
import com.summercampquest.campquest.exceptions.UsernameExistsException;
import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.User;
import com.summercampquest.campquest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.security.Principal;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class UserController extends ExceptionHandlingController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User registrationInfo) throws UserNotFoundException, UsernameExistsException, EmailExistsException {

       User userInfo = userService.register(
               registrationInfo.getFirstName(),
               registrationInfo.getLastName(),
               registrationInfo.getEmail(),
               registrationInfo.getAge(),
               registrationInfo.getGrade(),
               registrationInfo.getPhone(),
               registrationInfo.getUsername(),
               registrationInfo.getPassword());
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);


    }


}
