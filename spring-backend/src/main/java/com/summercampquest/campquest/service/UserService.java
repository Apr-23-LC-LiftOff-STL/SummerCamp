package com.summercampquest.campquest.service;


import com.summercampquest.campquest.models.Role;
import com.summercampquest.campquest.models.User;
import com.summercampquest.campquest.models.data.RoleRepository;
import com.summercampquest.campquest.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerNewUser(User user){
        Role role = roleRepository.findById("User").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userRepository.save(user);

    }

    //create default 2 roles & 1 admin account whenever the application is getting build
    public void initRolesAndUsers(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserName("admin123");
        adminUser.setUserEmail("admin123@gmail.com");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin_first_name");
        adminUser.setUserLastName("admin_last_name");

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);

        /*User user = new User();
        user.setUserFirstName("madhav");
        user.setUserLastName("karthik");
        user.setUserName("madhav123");
        user.setUserEmail("madhav123@gmail.com");
        user.setUserPassword(getEncodedPassword("madhav@pass"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userRepository.save(user);*/

    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

}
