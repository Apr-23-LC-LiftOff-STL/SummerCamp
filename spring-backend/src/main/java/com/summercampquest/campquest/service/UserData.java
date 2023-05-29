package com.summercampquest.campquest.service;

import com.summercampquest.campquest.models.User;
import com.summercampquest.campquest.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserData {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {

        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);//.orElseThrow(()-> new UserNotFoundException("User not found"));
    }

    public Boolean findUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean findEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User findUserByToken(String token){
        return userRepository.findUserByToken(token);
    }


}

