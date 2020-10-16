package com.kaito.game.Controller;

import com.kaito.game.Controller.Request.RoomCreateRequest;
import com.kaito.game.DTO.RoomDTO;
import com.kaito.game.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ROLE_GAMER')")
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping("/create")
    public RoomDTO createRoom(@RequestBody RoomCreateRequest roomCreateRequest ){
        RoomDTO roomDTO =  roomService.createRoom(roomCreateRequest);
        System.out.println(roomDTO);
        return roomDTO;
    }
}
