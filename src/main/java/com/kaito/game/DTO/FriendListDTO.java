package com.kaito.game.DTO;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class FriendListDTO {
    List<FriendDTO> onlineUsers = new LinkedList<>();
    List<FriendDTO> offlineUsers = new LinkedList<>();
    public void addOnline(String name) {
        onlineUsers.add(new FriendDTO(name));
    }

    public void addOffline(String name) {
        offlineUsers.add(new FriendDTO(name));
    }
}
