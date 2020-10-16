package com.kaito.game.BO;

import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Factory.GameFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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
