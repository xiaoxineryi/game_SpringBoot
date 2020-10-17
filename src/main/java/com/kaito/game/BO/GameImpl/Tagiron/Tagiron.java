package com.kaito.game.BO.GameImpl.Tagiron;

import com.kaito.game.BO.GameBO;

import javax.websocket.Session;
import java.util.Hashtable;

public class Tagiron implements GameBO {
    Hashtable<String, Session> players;
    int tempNUmber;

    @Override
    public Object initGame() {
        return new InfoDTO("kaito",1);
    }

    @Override
    public Object excute(Object o) {
        return new InfoDTO("kaito",2);
    }

    @Override
    public void setPlayers(Hashtable<String, Session> players) {
        this.players = players;
    }

    @Override
    public void setTempNumber(int tempNumber) {
        this.tempNUmber = tempNumber;
    }
}
