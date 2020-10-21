package com.kaito.game.BO.GameImpl;

import com.kaito.game.BO.Base.BaseRequest;
import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.GameBO;
import com.kaito.game.BO.Plugin.GameExtra;
import com.kaito.game.BO.RoomBO;

import javax.websocket.Session;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public  class GameBOImpl implements GameBO {
    Hashtable<String, Session> players;
    GameExtra gameExtra;
    @Override
    public  void initGame(RoomBO roomBO, String className) throws Exception {
        String path = "com.kaito.game.BO.Plugin.";
        Class clz = Class.forName(path+className+"."+className);
        Constructor constructor = clz.getConstructor(null);
        GameExtra game = (GameExtra) constructor.newInstance();
        gameExtra = game;

        this.setPlayers(roomBO.getPlayers());
        BaseResponse o =  gameExtra.initGame(new ArrayList<String>(players.keySet()));
        sendObject(o);

    }

    @Override
    public void execute(BaseRequest o) {
        BaseResponse baseResponse = gameExtra.execute(o);
    }

    private void sendObject(BaseResponse baseResponse){
        List<String> names = baseResponse.getReceivers();
        for (String name:names){
            if (players.keySet().contains(name)){
                players.get(name).getAsyncRemote().sendObject(baseResponse);
            }
        }
    }
    @Override
    public void setPlayers(Hashtable<String, Session> players) {
        this.players = players;
    }



    @Override
    public void removePlayer(String name) {
        players.remove(name);
    }
}
