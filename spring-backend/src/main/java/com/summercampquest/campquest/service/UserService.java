package com.summercampquest.campquest.service;


import com.summercampquest.campquest.exceptions.EmailExistsException;
import com.summercampquest.campquest.exceptions.UserNotFoundException;
import com.summercampquest.campquest.exceptions.UsernameExistsException;
import com.summercampquest.campquest.models.User;
import com.summercampquest.campquest.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Service
public interface UserService {


    User register(String firstName, String lastName, String email, Integer age, Integer grade, String phone, String username, String password) throws UsernameExistsException, EmailExistsException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

}























