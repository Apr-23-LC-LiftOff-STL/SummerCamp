package com.campquest.service;


import com.campquest.exception.UserNotFoundException;
import com.campquest.models.User;
import com.campquest.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public User addUser(User user){

        return userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(Integer id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found"));
    }

    public Boolean findUserExists(String username){
        return userRepository.existsByUsername(username);
    }

    public Boolean findEmailExists(String email){
        return userRepository.existsByEmail(email);
    }

    public User findUserByEmailAndPassword(String email, String password){
        return userRepository.findByEmailAndPassword(email,password);
    }



}
