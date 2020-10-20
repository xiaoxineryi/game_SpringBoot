package com.kaito.game.WebSocket;

import com.kaito.game.Config.MyEndpointConfig;

import com.kaito.game.DTO.UserDTO;
import com.kaito.game.Security.SecurityTokenUtil;
import com.kaito.game.Service.RoomService;
import com.kaito.game.Utils.SecurityUtil;
import com.kaito.game.Utils.WsDecoder;
import com.kaito.game.Utils.WsEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;

@ServerEndpoint(value = "/room/{roomID}/{token}",configurator = MyEndpointConfig.class
        ,encoders = {WsEncoder.class}, decoders = {WsDecoder.class})
@Slf4j
@Component
public class WsController {
    @Qualifier(value = "roomServiceImpl")
    @Autowired
    RoomService roomService;
    @Autowired
    SecurityTokenUtil securityTokenUtil;
    @OnOpen
    public void OnOpen(Session session,
                       @PathParam("roomID") int roomID,
                       @PathParam("token") String token) throws IOException {
        if (securityTokenUtil.validateToken(SecurityUtil.getUserName(),token)){
            System.out.println("成功");
            roomService.enterRoom(roomID,SecurityUtil.getUserName(),session);
        }else {
            session.getAsyncRemote().sendObject("身份信息错误");
            session.close();
        }
    }


    @OnMessage
    public String OnMessage(String  o){
        return o;
    }
    @OnClose
    public void OnClose(Session session, @PathParam("roomID") int roomID,
                        @PathParam("token") String token){
        roomService.quitRoom(roomID, SecurityUtil.getUserName());
    }

}
