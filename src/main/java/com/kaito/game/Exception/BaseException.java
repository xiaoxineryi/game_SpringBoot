package com.kaito.game.Exception;

import com.kaito.game.Utils.StatusEnum;
import lombok.Getter;

@Getter
public class BaseException extends Exception{
    protected StatusEnum statusEnum;

    public BaseException(StatusEnum status){
        this.statusEnum = status;
    }

}
