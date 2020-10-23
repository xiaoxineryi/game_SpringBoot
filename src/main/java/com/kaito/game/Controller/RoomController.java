package com.kaito.game.Controller;

import com.kaito.game.BO.Base.BaseRequest;
import com.kaito.game.Controller.Request.RoomCreateRequest;
import com.kaito.game.DTO.RoomDTO;
import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_GAMER')")
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping("/create")
    public RoomDTO createRoom(@RequestBody RoomCreateRequest roomCreateRequest ){
        RoomDTO roomDTO =  roomService.createRoom(roomCreateRequest);
        return roomDTO;
    }
    
    @GetMapping("/start")
    public void startGame(@PathParam("roomID") int roomID) throws Exception {
        roomService.startGame(roomID);
    }

    @PostMapping("/play")
    public void play(@RequestParam int roomID, @RequestBody Object object) throws CustomerException {
        System.out.println(roomID);
        roomService.play(roomID,object);
    }

    @GetMapping("/getRooms")
    public List<RoomDTO> getRooms(){
        return roomService.getAllRooms();
    }
}
