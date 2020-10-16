package com.kaito.game.Controller.Request;

import lombok.Data;

@Data
public class RoomCreateRequest {
    String userName;
    int type;
    int maxNumber;
}
