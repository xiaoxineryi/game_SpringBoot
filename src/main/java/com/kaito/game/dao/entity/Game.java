package com.kaito.game.dao.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameID;

    private String gameName;

    public Game(int gameID, String gameName) {
        this.gameID = gameID;
        this.gameName = gameName;
    }

}
