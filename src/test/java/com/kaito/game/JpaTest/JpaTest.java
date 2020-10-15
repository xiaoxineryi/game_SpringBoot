package com.kaito.game.JpaTest;

import com.kaito.game.Config.JpaConfig;
import com.kaito.game.GameApplication;

import com.kaito.game.dao.entity.GameEntity;
import com.kaito.game.dao.repository.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameApplication.class)
public class JpaTest {
    @Autowired
    GameRepository gameRepository;
    @Test
    public void test(){
        gameRepository.save(new GameEntity(2,"逻辑对决"));
    }
}
