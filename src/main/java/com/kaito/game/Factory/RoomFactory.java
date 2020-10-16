package com.kaito.game.Factory;

import com.kaito.game.BO.RoomBO;
import com.kaito.game.Controller.Request.RoomCreateRequest;

public interface RoomFactory {
    public RoomBO createRoomBO(RoomCreateRequest roomCreateRequest,int roomID);
}
