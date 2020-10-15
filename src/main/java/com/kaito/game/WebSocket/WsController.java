package com.kaito.game.WebSocket;

import com.kaito.game.Utils.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;

@ServerEndpoint(value = "/page_room/{userName}")
@Slf4j
@Component
public class WsController {

    @OnOpen
    public void OnOpen(Session session, @PathParam("userName") String userName){
        SessionManager.put(userName,session);
        sendMessage("好友"+userName+"已经上线");
        System.out.println(session);

    }

    @OnMessage
    public void OnMessage(String msg,@PathParam("userName") String userName){
        sendMessage(userName+"发送消息:"+msg);
    }
    @OnClose
    public void OnClose(Session session,@PathParam("userName") String userName){
        SessionManager.remove(userName);
        sendMessage("用户"+userName+"退出");
    }

    public void sendMessage(String msg){
        Collection<Session> sessions = SessionManager.getSessionList();
        for (Session session:sessions){
            session.getAsyncRemote().sendText(msg);
        }
    }
}
