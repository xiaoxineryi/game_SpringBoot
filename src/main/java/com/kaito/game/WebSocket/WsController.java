package com.kaito.game.WebSocket;

import com.kaito.game.Config.MyEndpointConfig;

import com.kaito.game.DTO.UserDTO;
import com.kaito.game.Service.RoomService;
import com.kaito.game.Utils.WsDecoder;
import com.kaito.game.Utils.WsEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;

@ServerEndpoint(value = "/room/{userName}/{roomID}",configurator = MyEndpointConfig.class
        ,encoders = {WsEncoder.class}, decoders = {WsDecoder.class})
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
    public String OnMessage(String  o){
        return o;
    }
    @OnClose
    public void OnClose(Session session,@PathParam("userName") String userName,
                        @PathParam("roomID") int roomID){
        roomService.quitRoom(roomID,userName);
    }

}
