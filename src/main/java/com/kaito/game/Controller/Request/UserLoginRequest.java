package com.kaito.game.Controller.Request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }
}
