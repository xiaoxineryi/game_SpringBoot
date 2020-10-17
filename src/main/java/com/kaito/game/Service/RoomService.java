package com.kaito.game.Service;

import com.kaito.game.Controller.Request.RoomCreateRequest;
import com.kaito.game.DTO.RoomDTO;
import com.kaito.game.Exception.CustomerException;

public interface RoomService {
    RoomDTO createRoom(RoomCreateRequest roomCreateRequest);

    Object startGame(int roomID) throws Exception;

    Object play(int roomID, Object o) throws CustomerException;

    void removeSession(String userName);
    void removeRoomSession(int roomID,String userName);
}
