package com.kaito.game.Service;

import com.kaito.game.dao.entity.Game;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GameService {
    List<Game> getGames();
}
