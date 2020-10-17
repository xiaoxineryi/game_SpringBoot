package com.kaito.game.BO.GameImpl;

import com.kaito.game.BO.GameBO;
import com.kaito.game.BO.GameImpl.Tagiron.InfoDTO;

import javax.websocket.Session;
import java.util.Hashtable;

public abstract class GameBOImpl implements GameBO {
    Hashtable<String, Session> players;
    int tempNUmber;

    @Override
    public abstract Object initGame();

    @Override
    public abstract Object excute(Object o);

    @Override
    public void setPlayers(Hashtable<String, Session> players) {
        this.players = players;
    }

    @Override
    public void setTempNumber(int tempNumber) {
        this.tempNUmber = tempNumber;
    }

    @Override
    public void removePlayer(String name) {
        players.remove(name);
        tempNUmber -- ;
    }
}
