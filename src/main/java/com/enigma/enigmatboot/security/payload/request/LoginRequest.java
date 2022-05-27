package com.enigma.enigmatboot.security.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
