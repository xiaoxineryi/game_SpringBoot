package com.kaito.game.BO;

import lombok.Data;

import javax.websocket.Session;
import java.util.Hashtable;

@Data
public class RoomBO {
    Integer roomID;
    Integer maxNumber;
    Integer tempNumber;
    int type;

    Hashtable<String, Session> players;
    GameBO gameBO;

    public RoomBO(Integer roomID, Integer maxNumber, Integer tempNumber, int type) {
        this.roomID = roomID;
        this.maxNumber = maxNumber;
        this.tempNumber = tempNumber;
        this.type = type;
        players = new Hashtable<>();
    }


}
