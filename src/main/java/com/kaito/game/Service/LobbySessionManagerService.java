package com.kaito.game.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;


public interface LobbySessionManagerService {

    public void put(String userName,Session session);
    public  void remove(String userName);
    public  Session getSession(String userName);
    public  Collection<Session> getSessionList();
}
