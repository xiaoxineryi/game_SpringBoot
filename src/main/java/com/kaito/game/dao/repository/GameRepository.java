package com.kaito.game.dao.repository;

import com.kaito.game.dao.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer> {
    Game findAllByGameID(int gameID);
}
