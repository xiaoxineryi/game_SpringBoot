package com.kaito.game.Controller;


import com.kaito.game.Controller.Request.AddFriendRequest;
import com.kaito.game.Controller.Request.UserLoginRequest;
import com.kaito.game.Controller.Request.UserRegisterRequest;
import com.kaito.game.DTO.UserDTO;
import com.kaito.game.Exception.CustomerException;
import com.kaito.game.Service.FriendService;
import com.kaito.game.Service.GameService;
import com.kaito.game.Service.UserService;
import com.kaito.game.dao.entity.GameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")

public class AccountController {
    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserLoginRequest userLoginRequest) throws CustomerException {
        return userService.login(userLoginRequest);
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserRegisterRequest userRegisterRequest) throws CustomerException {
        return userService.register(userRegisterRequest);
    }

    @PostMapping("/addfriend")
    public Boolean addfriend(@RequestBody AddFriendRequest addFriendRequest) throws CustomerException {
        return friendService.addFriend(addFriendRequest);
    }
}
