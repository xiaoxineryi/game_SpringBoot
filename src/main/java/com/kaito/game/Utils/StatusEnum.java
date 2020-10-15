package com.kaito.game.Utils;

import lombok.Getter;

@Getter
public enum StatusEnum {
    SUCCESS(200,""),
    WRONG_TOKEN_FOR_USER(401,"自动登录已过期，请重新登录"),
    WRONG_ACCOUNT_OR_PASSWORD(402,"帐号或密码错误"),
    FORBIDDEN(403,"请登录后访问");

    StatusEnum(int code,String messgage){
        this.code = code;
        this.message = messgage;
    }
    private int code;
    private String message;

    public boolean isSuccess() {
        return code == StatusEnum.SUCCESS.code;
    }
}
