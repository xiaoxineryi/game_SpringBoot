package com.kaito.game.Controller;


import com.kaito.game.Factory.GameFactory;
import com.kaito.game.Service.GameService;
import com.kaito.game.Service.ServiceImpl.GameServiceImpl;
import com.kaito.game.dao.entity.GameEntity;
import com.kaito.game.dao.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/game")
@Slf4j

public class GameController {


    @Autowired
    GameService gameService;
    @GetMapping("/getGame")
    public GameEntity getGame(){
        log.info("你好");
        GameEntity gameEntity = gameService.getGames().get(0);
        System.out.println(gameEntity);
        return gameEntity;
    }
}
