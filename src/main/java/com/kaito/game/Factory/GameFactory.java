package com.kaito.game.Factory;


import com.kaito.game.BO.GameBO;
import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Service.GameService;
import com.kaito.game.dao.entity.GameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameFactory {

    public  GameEntity createGame(Integer id, String name,String gameClass) {
        return new GameEntity(id,name,gameClass);
    }
}
