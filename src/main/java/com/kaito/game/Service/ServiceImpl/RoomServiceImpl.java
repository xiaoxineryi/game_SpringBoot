package com.kaito.game.Service.ServiceImpl;

import com.kaito.game.BO.Base.BaseRequest;
import com.kaito.game.BO.GameBO;
import com.kaito.game.BO.GameImpl.GameBOImpl;
import com.kaito.game.BO.RoomBO;
import com.kaito.game.Controller.Request.RoomCreateRequest;
import com.kaito.game.DTO.RoomDTO;
import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Factory.RoomFactory;
import com.kaito.game.Service.GameService;
import com.kaito.game.Service.RoomService;
import com.kaito.game.Utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.lang.reflect.Constructor;
import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {
    Hashtable<Integer, RoomBO> rooms = new Hashtable<>();

    @Autowired
    RoomFactory roomFactory;
    @Autowired
    GameService gameService;


    @Override
    public RoomDTO createRoom(RoomCreateRequest roomCreateRequest) {
        Random r = new Random();
        int random = r.nextInt(1000);
        while (rooms.containsKey(random)) {
            random = r.nextInt(1000);
        }
        RoomBO roomBO = roomFactory.createRoomBO(roomCreateRequest, random);
        rooms.put(random, roomBO);
        System.out.println(random);
        return new RoomDTO(roomBO);
    }

    @Override
    public void startGame(int roomID) throws Exception {
        RoomBO roomBO = rooms.get(roomID);
        if (roomBO == null) {
            throw new CustomerException(StatusEnum.CANT_FIND_ROOM);
        }
        String className = gameService.getGameClassById(roomBO.getType());
        GameBO gameBO = new GameBOImpl();
        roomBO.setGameBO(gameBO);
        gameBO.initGame(roomBO, className);
    }

    @Override
    public void play(int roomID, Object baseRequest) throws CustomerException {
        RoomBO roomBO = rooms.get(roomID);
        if (roomBO == null) {
            throw new CustomerException(StatusEnum.CANT_FIND_ROOM);
        }
        System.out.println(baseRequest.toString());
        roomBO.getGameBO().execute(baseRequest);
    }

    public void removeRoomSession(int roomID, String userName) {
        for (RoomBO roomBO : rooms.values()) {
            if (roomBO.getRoomID() == roomID) {
                roomBO.removeUser(userName);
            }
        }
    }

    @Override
    public void enterRoom(int roomID, String userName, Session session) {
        RoomBO roomBO = rooms.get(roomID);
        roomBO.addUser(userName, session);

    }

    @Override
    public boolean checkAtRoom(String userName) {
        for (RoomBO roomBO : rooms.values()) {
            if (roomBO.getPlayers().keySet().contains(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void quitRoom(int roomID, String userName) {
        RoomBO roomBO = rooms.get(roomID);
        roomBO.removeUser(userName);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        List<RoomDTO> roomDTOS = new LinkedList<>();
        for (RoomBO roomBO : rooms.values()) {
            roomDTOS.add(new RoomDTO(roomBO));
        }
        return roomDTOS;
    }


}
