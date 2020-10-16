package com.kaito.game.BO;

import javax.websocket.Session;
import java.util.Hashtable;

public interface GameBO {
    Object initGame();
    Object excute(Object o);

    void setPlayers(Hashtable<String, Session> players);
    void setTempNumber(int tempNumber);
}
