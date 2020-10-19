package com.kaito.game.Utils;

import lombok.Getter;

@Getter
public enum StatusEnum {
    SUCCESS(200, ""),
    WRONG_TOKEN_FOR_USER(401, "自动登录已过期，请重新登录"),
    WRONG_ACCOUNT_OR_PASSWORD(402, "帐号或密码错误"),
    FORBIDDEN(403, "请登录后访问"),
    CANT_FIND_GAME(404, "您输入的游戏编号不存在"),
    CANT_FIND_ROOM(405, "您输入的房间号不存在"),
    REPEAT_ACCOUNT(406, "账号已被注册");

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public boolean isSuccess() {
        return code == StatusEnum.SUCCESS.code;
    }
}
