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

    private String userA;

    private String userB;

    public FriendsEntity(int id, String userA, String userB) {
        this.id = id;
        this.userA = userA;
        this.userB = userB;
    }


    public FriendsEntity() {

    }
}
