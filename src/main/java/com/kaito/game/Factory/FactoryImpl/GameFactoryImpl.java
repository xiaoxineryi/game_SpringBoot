package com.kaito.game.Factory.FactoryImpl;

import com.kaito.game.Factory.GameFactory;

import com.kaito.game.dao.entity.GameEntity;
import org.springframework.stereotype.Service;

@Service
public class GameFactoryImpl implements GameFactory {
    @Override
    public GameEntity createGame(Integer id, String name) {
        return new GameEntity(id,name);
    }
}
