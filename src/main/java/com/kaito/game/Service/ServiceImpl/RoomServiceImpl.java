package com.kaito.game.Service.ServiceImpl;

import com.kaito.game.BO.RoomBO;
import com.kaito.game.Controller.Request.RoomCreateRequest;
import com.kaito.game.DTO.RoomDTO;
import com.kaito.game.Factory.RoomFactory;
import com.kaito.game.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;

@Service
public class RoomServiceImpl implements RoomService {
    Hashtable<Integer, RoomBO> rooms = new Hashtable<>();

    @Autowired
    RoomFactory roomFactory;
    @Override
    public RoomDTO createRoom(RoomCreateRequest roomCreateRequest) {
        RoomBO roomBO =  roomFactory.createRoomBO(roomCreateRequest,1);
        return new RoomDTO(roomBO);
    }
}
