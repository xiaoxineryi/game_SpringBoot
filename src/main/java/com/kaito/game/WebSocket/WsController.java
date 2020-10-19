package com.kaito.game.WebSocket;

import com.kaito.game.Config.MyEndpointConfig;

import com.kaito.game.Service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;

@ServerEndpoint(value = "/room/{userName}/{roomID}",configurator = MyEndpointConfig.class)
@Slf4j
@Component
public class WsController {
    @Qualifier(value = "roomServiceImpl")
    @Autowired
    RoomService roomService;

    @OnOpen
    public void OnOpen(Session session, @PathParam("userName") String userName,
                       @PathParam("roomID") int roomID){
        roomService.enterRoom(roomID,userName,session);
    }


    @OnMessage
    public void OnMessage(String msg,@PathParam("userName") String userName,
                          @PathParam("roomID") int roomID){

    }
    @OnClose
    public void OnClose(Session session,@PathParam("userName") String userName,
                        @PathParam("roomID") int roomID){
        roomService.quitRoom(roomID,userName);
    }



}
