package com.kaito.game.Controller;


import com.kaito.game.Controller.Request.UserLoginRequest;
import com.kaito.game.DTO.UserDTO;
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

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserLoginRequest userLoginRequest){
        userService.getUserByUserName(userLoginRequest.getUserName());
        return new UserDTO(userLoginRequest.getUserName());
    }
}
