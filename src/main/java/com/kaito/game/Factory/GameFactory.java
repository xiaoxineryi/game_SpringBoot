package com.kaito.game.Factory;

import com.kaito.game.dao.entity.Game;

public interface GameFactory {
    Game createGame(Integer id,String name);
}
