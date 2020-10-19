package com.kaito.game.BO;

import com.kaito.game.DTO.UserDTO;
import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Factory.GameFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Hashtable;

@Data
@Slf4j
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


    public void removeUser(String userName) {
        players.remove(userName);
        tempNumber--;
        if (gameBO != null){
            gameBO.removePlayer(userName);
        }
    }
    public void addUser(String userName,Session session){
        players.put(userName,session);
        tempNumber ++;
        sendUpline(userName);
        log.info("用户进入房间");
    }

    private void sendUpline(String userName) {
        for (Session session: players.values()){
            session.getAsyncRemote().sendObject(new UserDTO(userName,"1"));

        }
    }


}
