package com.summercampquest.campquest.models;


import java.io.Serializable;

public class ForgotPassword implements Serializable {

    private String email;

    public ForgotPassword() {
    }

    public ForgotPassword(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
