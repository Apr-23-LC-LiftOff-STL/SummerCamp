package com.summercampquest.campquest.models;

public class ResetPassword {

    private String password;
    private String resetPassword;
    private String token;

    public ResetPassword(String password, String resetPassword, String token) {
        this.password = password;
        this.resetPassword = resetPassword;
        this.token = token;
    }

    public ResetPassword() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
