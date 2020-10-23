package com.kaito.game.Controller.Request;

import lombok.Data;

@Data
public class RoomCreateRequest {
    String userName;
    int gameType;
    int maxNumber;
}
