package com.kaito.game.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    private String userName;

    private String userEmail;

    private String userPwd;

    private String userAuth;

    private String userToken;

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public String getUserAuth() {
        return userAuth;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserAuth(String userAuth) {
        this.userAuth = userAuth;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
