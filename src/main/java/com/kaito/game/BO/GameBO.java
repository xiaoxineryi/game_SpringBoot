package com.kaito.game.BO;

import javax.websocket.Session;
import java.util.Hashtable;

public interface GameBO {
    Object initGame(RoomBO roomBO, String className) throws Exception;
    Object excute(Object o);

    void setPlayers(Hashtable<String, Session> players);
    void setTempNumber(int tempNumber);

    void removePlayer(String name);
}
