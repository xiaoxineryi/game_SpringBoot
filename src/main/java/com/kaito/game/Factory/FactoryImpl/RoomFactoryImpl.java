package com.kaito.game.Factory.FactoryImpl;

import com.kaito.game.BO.RoomBO;
import com.kaito.game.Controller.Request.RoomCreateRequest;
import com.kaito.game.Factory.RoomFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomFactoryImpl implements RoomFactory {

    @Override
    public RoomBO createRoomBO(RoomCreateRequest roomCreateRequest,int roomID) {
        RoomBO roomBO =  new RoomBO(roomID,roomCreateRequest.getMaxNumber(),0,
                roomCreateRequest.getGameType());

        return roomBO;
    }
}
