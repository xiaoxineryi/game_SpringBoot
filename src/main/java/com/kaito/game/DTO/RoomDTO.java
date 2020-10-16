package com.kaito.game.DTO;

import com.kaito.game.BO.RoomBO;
import lombok.Data;

@Data
public class RoomDTO {
    int roomID;
    int tempNumber;
    int maxNumber;
    int type;

    public RoomDTO(RoomBO roomBO){
        roomID = roomBO.getRoomID();
        tempNumber = roomBO.getTempNumber();
        maxNumber = roomBO.getMaxNumber();
        type = roomBO.getType();
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomID=" + roomID +
                ", tempNumber=" + tempNumber +
                ", maxNumber=" + maxNumber +
                ", type=" + type +
                '}';
    }
}
