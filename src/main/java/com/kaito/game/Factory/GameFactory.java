package com.kaito.game.Factory;


import com.kaito.game.dao.entity.GameEntity;

public interface GameFactory {
    GameEntity createGame(Integer id, String name);
}
