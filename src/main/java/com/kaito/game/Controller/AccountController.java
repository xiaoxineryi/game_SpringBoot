package com.kaito.game.Controller;


import com.kaito.game.Service.GameService;
import com.kaito.game.Utils.SessionManager;
import com.kaito.game.dao.entity.GameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@PreAuthorize("hasRole('ROLE_GAMER')")
public class AccountController {
    @Autowired
    GameService gameService;


    @GetMapping("/login")
    public GameEntity login(){
        return gameService.getGames().get(0);
    }
}
