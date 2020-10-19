package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.GameImpl.GameBOImpl;
import com.kaito.game.BO.Plugin.GameExtra;
import com.kaito.game.BO.RoomBO;

public class Tagiron implements GameExtra {

    @Override
    public Object initGame() {
        return new InfoDTO("kaito",1);
    }

    @Override
    public Object excute(Object o) {
        return new InfoDTO("kaito",2);
    }

}
