package com.kaito.game.BO;

import com.kaito.game.BO.Base.BaseRequest;

import javax.websocket.Session;
import java.util.Hashtable;

public interface GameBO {
    void initGame(RoomBO roomBO, String className) throws Exception;

    void execute(BaseRequest baseRequest);

    void setPlayers(Hashtable<String, Session> players);

    void removePlayer(String name);
}
