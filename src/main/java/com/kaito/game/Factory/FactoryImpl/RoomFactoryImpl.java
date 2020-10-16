package com.kaito.game.Factory.FactoryImpl;

import com.kaito.game.BO.RoomBO;
import com.kaito.game.Controller.Request.RoomCreateRequest;
import com.kaito.game.Factory.RoomFactory;
import com.kaito.game.Service.LobbySessionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RoomFactoryImpl implements RoomFactory {
    @Autowired
    LobbySessionManagerService lobbySessionManagerService;
    @Override
    public RoomBO createRoomBO(RoomCreateRequest roomCreateRequest,int roomID) {
        RoomBO roomBO =  new RoomBO(roomID,roomCreateRequest.getMaxNumber(),1,
                roomCreateRequest.getType());
        roomBO.getPlayers().put(roomCreateRequest.getUserName(),
                lobbySessionManagerService.getSession(roomCreateRequest.getUserName()));
        return roomBO;
    }
}
