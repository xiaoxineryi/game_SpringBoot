package com.kaito.game.WebSocket;

import com.kaito.game.Config.MyEndpointConfig;
import com.kaito.game.Service.LobbySessionManagerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;

@ServerEndpoint(value = "/page_room/{userName}",configurator = MyEndpointConfig.class)
@Slf4j
@Component
public class WsController {

    @Qualifier("lobbySessionManagerServiceImpl")
    @Autowired
    LobbySessionManagerService lobbySessionManagerService;

    @OnOpen
    public void OnOpen(Session session, @PathParam("userName") String userName){
        lobbySessionManagerService.put(userName,session);
        sendMessage("好友"+userName+"已经上线");
        System.out.println(session);

    }

    @OnMessage
    public void OnMessage(String msg,@PathParam("userName") String userName){
        sendMessage(userName+"发送消息:"+msg);
    }
    @OnClose
    public void OnClose(Session session,@PathParam("userName") String userName){
        lobbySessionManagerService.remove(userName);
        sendMessage("用户"+userName+"退出");
    }

    public void sendMessage(String msg){
        Collection<Session> sessions = lobbySessionManagerService.getSessionList();
        for (Session session:sessions){
            session.getAsyncRemote().sendText(msg);
        }
    }
}
