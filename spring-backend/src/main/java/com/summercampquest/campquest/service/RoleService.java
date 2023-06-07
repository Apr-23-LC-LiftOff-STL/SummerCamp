package com.summercampquest.campquest.service;


import com.summercampquest.campquest.models.Role;
import com.summercampquest.campquest.models.data.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public Role createNewRole(Role role){
        return roleRepository.save(role);
    }
}
