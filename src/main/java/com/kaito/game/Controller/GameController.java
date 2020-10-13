package com.kaito.game.Controller;

import com.kaito.game.dao.entity.Game;
import com.kaito.game.dao.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/game")
@Slf4j
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @GetMapping("/")
    public void addGame(){
        log.info("你好");
    }
}
