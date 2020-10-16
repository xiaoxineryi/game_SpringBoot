package com.kaito.game.Service;

import com.kaito.game.Controller.Request.RoomCreateRequest;
import com.kaito.game.DTO.RoomDTO;

public interface RoomService {
    RoomDTO createRoom(RoomCreateRequest roomCreateRequest);
}
