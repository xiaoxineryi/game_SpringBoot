package com.kaito.game.BO.GameImpl;

import com.kaito.game.BO.GameBO;
import com.kaito.game.BO.Plugin.GameExtra;
import com.kaito.game.BO.RoomBO;

import javax.websocket.Session;
import java.lang.reflect.Constructor;
import java.util.Hashtable;

public  class GameBOImpl implements GameBO {
    Hashtable<String, Session> players;
    int tempNumber;
    GameExtra gameExtra;
    @Override
    public  Object initGame(RoomBO roomBO, String className) throws Exception {
        String path = "com.kaito.game.BO.Plugin.";
        Class clz = Class.forName(path+className+"."+className);
        Constructor constructor = clz.getConstructor(null);
        GameExtra game = (GameExtra) constructor.newInstance();
        gameExtra = game;
        this.setPlayers(roomBO.getPlayers());
        this.setTempNumber(roomBO.getTempNumber());
        return gameExtra.initGame();
    }

    @Override
    public  Object excute(Object o){
        return gameExtra.excute(o);
    }
    public void sendObject(Object o){
        for (Session session:players.values()){
            session.getAsyncRemote().sendObject(o);
        }
    }
    @Override
    public void setPlayers(Hashtable<String, Session> players) {
        this.players = players;
    }

    @Override
    public void setTempNumber(int tempNumber) {
        this.tempNumber = tempNumber;
    }

    @Override
    public void removePlayer(String name) {
        players.remove(name);
        tempNumber -- ;
    }
}
