package com.kaito.game.BO.Plugin;

import com.kaito.game.BO.Base.BaseResponse;

import java.util.ArrayList;
import java.util.List;

public interface GameExtra {
    public List<BaseResponse> initGame(ArrayList<String> users);
}
