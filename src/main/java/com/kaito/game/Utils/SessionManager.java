package com.kaito.game.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;


public class SessionManager {
    private SessionManager(){}
    public static Hashtable<String, Session> sessionMap = new Hashtable<>();

    public static void put(String userName,Session session){
        sessionMap.put(userName,session);
    }
    public static void remove(String userName){
        sessionMap.remove(userName);
    }
    public static Session getSession(String userName){
        return sessionMap.get(userName);
    }
    public static Collection<Session> getSessionList(){
        return sessionMap.values();
    }
}
