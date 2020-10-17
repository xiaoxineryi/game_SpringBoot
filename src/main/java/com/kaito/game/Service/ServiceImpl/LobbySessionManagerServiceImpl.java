package com.kaito.game.Service.ServiceImpl;

import com.kaito.game.Service.LobbySessionManagerService;
import com.kaito.game.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.Collection;
import java.util.Hashtable;

@Service
public class LobbySessionManagerServiceImpl implements LobbySessionManagerService {
    private Hashtable<String,Session> sessionHashtable = new Hashtable<>();

    @Autowired
    RoomService roomService;
    @Override
    public void put(String userName, Session session) {
        sessionHashtable.put(userName,session);
    }

    @Override
    public void remove(String userName) {
        sessionHashtable.remove(userName);
        roomService.removeSession(userName);
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
