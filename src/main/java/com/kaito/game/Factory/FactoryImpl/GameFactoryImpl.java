package com.kaito.game.Factory.FactoryImpl;

import com.kaito.game.Factory.GameFactory;
import com.kaito.game.dao.entity.Game;
import org.springframework.stereotype.Service;

@Service
public class GameFactoryImpl implements GameFactory {
    @Override
    public Game createGame(Integer id, String name) {
        return new Game(id,name);
    }
}
