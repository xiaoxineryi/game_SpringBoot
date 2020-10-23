package com.kaito.game.BO;

import javax.websocket.Session;
import java.util.Hashtable;

public interface GameBO {
    void initGame(RoomBO roomBO, String className) throws Exception;

    void execute(Object baseRequest);

    void setPlayers(Hashtable<String, Session> players);

    void removePlayer(String name);
}
