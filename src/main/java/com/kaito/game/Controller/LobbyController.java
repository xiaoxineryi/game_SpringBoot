package com.kaito.game.Controller;

import com.kaito.game.DTO.FriendListDTO;
import com.kaito.game.Service.FriendService;
import com.kaito.game.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lobby")
public class LobbyController {

    @Autowired
    FriendService friendService;

    @GetMapping(value = "/friendList")
    public FriendListDTO getFriendList(@RequestParam(value = "userName") String userName){
        FriendListDTO friendListDTO = friendService.getFriendList(userName);
        System.out.println(friendListDTO);
        return friendListDTO;
    }
}
