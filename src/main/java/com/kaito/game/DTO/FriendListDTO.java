package com.kaito.game.DTO;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class FriendListDTO {
    List<String> onlineUsers = new LinkedList<>();
    List<String> offlineUsers = new LinkedList<>();
    public void addOnline(String name) {
        onlineUsers.add(name);
    }

    public void addOffline(String name) {
        offlineUsers.add(name);
    }
}
