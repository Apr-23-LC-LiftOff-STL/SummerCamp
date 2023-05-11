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
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, UsernameExistsException, EmailExistsException {

       User userInfo = userService.register(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge(),
                user.getGrade(),
                user.getPhone(),
                user.getUsername(),
                user.getPassword());

       return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }



    //BasicAuthMethods
    /*
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
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



    @GetMapping("/getUsers")
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        User user = new User("Billy", "Madison", "johndoe@example.com", 25, 3, "555-1234", "test", "password");
        userList.add(user);
        return userList;
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

//        User _user = userService
//                .addUser(new User(user.getFirstName(),user.getLastName(),user.getEmail(), user.getAge(),user.getGrade(),user.getPhone(),user.getUsername(),user.getPassword(),user.getFavorites(),user.getProfilePictureLink()));
//
//        userService.addUser(_user);


        //userService.addUser(user);
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);

    }       // registerUser method

  */
}
