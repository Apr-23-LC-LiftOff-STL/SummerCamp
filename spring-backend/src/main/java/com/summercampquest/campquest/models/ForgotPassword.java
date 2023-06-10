package com.summercampquest.campquest.models;

import java.io.Serializable;

public class ForgotPassword implements Serializable {

    private String userEmail;

    public ForgotPassword() {
    }

    public ForgotPassword(String email) {
        this.userEmail = email;
    }

    public String getEmail() {
        return userEmail;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }
}
