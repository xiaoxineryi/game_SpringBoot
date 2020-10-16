package com.kaito.game.dao.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "game")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameID;

    private String gameName;

    private String gameClass;
    public GameEntity(int gameID, String gameName,String gameClass) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.gameClass = gameClass;
    }

    public GameEntity() {

    }
}
