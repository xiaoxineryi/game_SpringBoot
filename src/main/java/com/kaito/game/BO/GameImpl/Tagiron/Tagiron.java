package com.kaito.game.BO.GameImpl.Tagiron;

import com.kaito.game.BO.GameBO;
import com.kaito.game.BO.GameImpl.GameBOImpl;

import javax.websocket.Session;
import java.util.Hashtable;

public class Tagiron extends GameBOImpl {

    @Override
    public Object initGame() {
        return new InfoDTO("kaito",1);
    }

    @Override
    public Object excute(Object o) {
        return new InfoDTO("kaito",2);
    }

}
