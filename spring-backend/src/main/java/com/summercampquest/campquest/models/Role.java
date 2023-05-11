package com.summercampquest.campquest.models;

import static com.summercampquest.campquest.models.Authority.ADMIN_AUTHORITIES;
import static com.summercampquest.campquest.models.Authority.USER_AUTHORITIES;


public enum Role {

    ROLE_USER(USER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String ...authorities){
        this.authorities = authorities;
    }

    public String[] getAuthorities(){
        return authorities;
    }
}









