package com.kaito.game.xmlTest;

import com.kaito.game.Config.JpaConfig;
import com.kaito.game.GameApplication;
import com.kaito.game.Service.GameService;
import com.kaito.game.Service.ServiceImpl.GameServiceImpl;
import com.kaito.game.dao.entity.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameApplication.class)
public class xmlTest {
    @Autowired
    GameService gameService;
    @Test
    public void test(){
        List<Game> gameList = gameService.getGames();
        for (Game game:gameList) {
            System.out.println(game);
        }
    }
}
