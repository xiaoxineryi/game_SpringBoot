package com.kaito.game.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "friends")
public class FriendsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userAID;

    private int userBID;

    public FriendsEntity(int id, int userAID, int userBID) {
        this.id = id;
        this.userAID = userAID;
        this.userBID = userBID;
    }


    public FriendsEntity() {

    }
}
