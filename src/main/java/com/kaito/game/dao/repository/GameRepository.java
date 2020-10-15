package com.kaito.game.dao.repository;


import com.kaito.game.dao.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity,Integer> {
    GameEntity findAllByGameID(int gameID);
}
