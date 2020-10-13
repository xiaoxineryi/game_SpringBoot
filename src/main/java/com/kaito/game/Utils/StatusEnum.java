package com.kaito.game.Utils;

import lombok.Getter;

@Getter
public enum StatusEnum {
    SUCCESS(200,""),
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
