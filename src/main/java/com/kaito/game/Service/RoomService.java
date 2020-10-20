package com.kaito.game.Service;

import com.kaito.game.Controller.Request.RoomCreateRequest;
import com.kaito.game.DTO.RoomDTO;
import com.kaito.game.Exception.CustomerException;

import javax.websocket.Session;
import java.util.List;

public interface RoomService {
    RoomDTO createRoom(RoomCreateRequest roomCreateRequest);

    Object startGame(int roomID) throws Exception;

    Object play(int roomID, Object o) throws CustomerException;

    void removeRoomSession(int roomID,String userName);

    void enterRoom(int roomID, String userName, Session session);

    boolean checkAtRoom(String userName);
    void quitRoom(int roomID, String userName);

    List<RoomDTO> getAllRooms();
}
