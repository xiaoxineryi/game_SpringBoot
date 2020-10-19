package com.kaito.game.Controller.Request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String userName;
    private String password;
    private String userEmail;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
