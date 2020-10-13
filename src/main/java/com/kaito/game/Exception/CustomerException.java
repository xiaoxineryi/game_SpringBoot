package com.kaito.game.Exception;

import com.kaito.game.Utils.StatusEnum;

public class CustomerException extends BaseException{

    public CustomerException(StatusEnum status) {
        super(status);
    }
}
