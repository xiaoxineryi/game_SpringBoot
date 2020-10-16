package com.kaito.game.Service.ServiceImpl;

import com.kaito.game.Service.LobbySessionManagerService;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.Collection;
import java.util.Hashtable;

@Service
public class LobbySessionManagerServiceImpl implements LobbySessionManagerService {
    private Hashtable<String,Session> sessionHashtable = new Hashtable<>();
    @Override
    public void put(String userName, Session session) {
        sessionHashtable.put(userName,session);
    }

    @Override
    public void remove(String userName) {
        sessionHashtable.remove(userName);
    }

    @Override
    public Session getSession(String userName) {
        return sessionHashtable.get(userName);
    }


    @Override
    public Collection<Session> getSessionList() {
        return sessionHashtable.values();
    }
}
