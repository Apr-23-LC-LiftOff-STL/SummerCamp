package com.summercampquest.campquest.controllers;

import com.summercampquest.campquest.exceptions.EmailExistsException;
import com.summercampquest.campquest.exceptions.ExceptionHandlingController;
import com.summercampquest.campquest.exceptions.UserNotFoundException;
import com.summercampquest.campquest.exceptions.UsernameExistsException;
import com.summercampquest.campquest.models.Camp;
import com.summercampquest.campquest.models.User;
import com.summercampquest.campquest.models.UserPrincipal;
import com.summercampquest.campquest.security.JWTTokenProvider;
import com.summercampquest.campquest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import static com.summercampquest.campquest.service.SecurityConstant.JWT_TOKEN_HEADER;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class UserController extends ExceptionHandlingController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(),user.getPassword());

        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJWTHeader(userPrincipal); //return json token headers

        return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
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


    private HttpHeaders getJWTHeader(UserPrincipal userPrincipal) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJWTToken(userPrincipal));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }





}
