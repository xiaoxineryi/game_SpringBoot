package com.kaito.game.BO.Plugin;

import com.kaito.game.BO.Base.BaseResponse;

import java.util.ArrayList;
import java.util.LinkedList;

public interface GameExtra {
    public BaseResponse excute(Object o);
    public BaseResponse initGame(ArrayList<String> users);
}
