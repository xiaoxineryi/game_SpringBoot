package com.kaito.game.Service;

import com.kaito.game.dao.entity.GameEntity;

import java.util.List;


public interface GameService {
    List<GameEntity> getGames();
}
