package com.kaito.game.BO.Plugin;

import com.kaito.game.BO.Base.BaseRequest;
import com.kaito.game.BO.Base.BaseResponse;

import java.util.ArrayList;
import java.util.LinkedList;

public interface GameExtra {
    public BaseResponse execute(BaseRequest baseRequest);
    public BaseResponse initGame(ArrayList<String> users);
}
