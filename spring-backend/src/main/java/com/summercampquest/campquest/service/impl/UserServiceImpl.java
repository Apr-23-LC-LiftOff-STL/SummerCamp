package com.summercampquest.campquest.service.impl;

import com.summercampquest.campquest.exceptions.EmailExistsException;
import com.summercampquest.campquest.exceptions.UsernameExistsException;
import com.summercampquest.campquest.models.Role;
import com.summercampquest.campquest.models.User;
import com.summercampquest.campquest.models.UserPrincipal;
import com.summercampquest.campquest.models.data.UserRepository;
import com.summercampquest.campquest.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    public static final String EMAIL_ALREADY_REGISTERED = "Email already registered";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username);
        if(user == null){
            LOGGER.error("Username not found: " + username);
            throw new UsernameNotFoundException("Username not found: " + username);
        }else{
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("Found username "+ username);
            return userPrincipal;
        }

    }


    private String encodePassword(String password) {

        return bCryptPasswordEncoder.encode(password);
    }


    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UsernameExistsException, EmailExistsException {
        User currentUser = findUserByUsername(currentUsername);
        User userByUsername = findUserByUsername(newUsername);
        User userByEmail = findUserByEmail(newEmail);

        if(StringUtils.isNotBlank(currentUsername)){


            if(currentUser == null){
                throw new UsernameNotFoundException("Username " + currentUsername + " not found");
            } //if username not in database


            Long currentUserId = currentUser.getId();
            if((userByUsername != null) && !currentUserId.equals(userByUsername.getId())){
                throw new UsernameExistsException(USERNAME_ALREADY_EXISTS);
            }

            User userByNewEmail = findUserByEmail(newEmail);
            if(userByNewEmail != null && !currentUserId.equals(userByUsername.getId())){
                throw new EmailExistsException(EMAIL_ALREADY_REGISTERED);
            }
            return currentUser;
        } //if currentUsername is not blank
        else {

            if(userByUsername != null){
                throw new EmailExistsException(USERNAME_ALREADY_EXISTS);
            }

            if(userByEmail != null){
                throw new EmailExistsException(EMAIL_ALREADY_REGISTERED);
            }
            return null;
        }
    }

    @Override
    public User register(String firstName, String lastName, String email, Integer age, Integer grade, String phone, String username, String password) throws UsernameExistsException, EmailExistsException {

            validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);

            User userRegistration = new User();

            String encodedPassword = encodePassword(password);

            userRegistration.setFirstName(firstName);
            userRegistration.setLastName(lastName);
            userRegistration.setEmail(email);
            userRegistration.setAge(age);
            userRegistration.setGrade(grade);
            userRegistration.setPhone(phone);
            userRegistration.setUsername(username);
            userRegistration.setPassword(encodedPassword);
            userRegistration.setRole(Role.ROLE_USER.name());
            userRegistration.setAuthorities(Role.ROLE_USER.getAuthorities());

            userRepository.save(userRegistration);

            // TODO: Remove logger code, for testing only
            //testing password functionality ************
            LOGGER.info("New User password: "+ password);
            //****************
            return userRegistration;

        }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}




















