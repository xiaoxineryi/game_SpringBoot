package com.kaito.game.DTO;

import com.kaito.game.BO.RoomBO;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class RoomDTO {
    int roomID;
    int tempNumber;
    int maxNumber;
    int type;
    Set<String> users;

    public RoomDTO(RoomBO roomBO){
        roomID = roomBO.getRoomID();
        tempNumber = roomBO.getTempNumber();
        maxNumber = roomBO.getMaxNumber();
        type = roomBO.getType();
        users = roomBO.getPlayers().keySet();
    }


}
