package com.kaito.game.Service.ServiceImpl;

import com.kaito.game.BO.GameBO;
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

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Optional;
import java.util.Random;

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
        int random  = r.nextInt(1000);
        while(rooms.containsKey(random)){
            random  = r.nextInt(1000);
        }
        RoomBO roomBO =  roomFactory.createRoomBO(roomCreateRequest,random);
        rooms.put(random,roomBO);
        System.out.println(random);
        return new RoomDTO(roomBO);
    }

    @Override
    public Object startGame(int roomID) throws Exception {
        RoomBO roomBO = rooms.get(roomID);
        if (roomBO == null){
            throw new CustomerException(StatusEnum.CANT_FIND_ROOM);
        }
        String className = gameService.getGameClassById(roomBO.getType());
        GameBO gameBO = initGame(roomBO,className);
        Object o = gameBO.initGame();
        return o;
    }

    private GameBO initGame(RoomBO roomBO, String className) throws Exception {
        String path = "com.kaito.game.BO.GameImpl.";
        Class clz = Class.forName(path+className);
        Constructor constructor = clz.getConstructor(null);
        GameBO gameBo = (GameBO) constructor.newInstance();
        gameBo.setPlayers(roomBO.getPlayers());
        gameBo.setTempNumber(roomBO.getTempNumber());
        return gameBo;
    }

}
