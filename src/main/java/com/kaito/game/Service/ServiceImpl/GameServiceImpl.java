package com.kaito.game.Service.ServiceImpl;

import com.kaito.game.Factory.GameFactory;
import com.kaito.game.Service.GameService;
import com.kaito.game.dao.entity.Game;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    GameFactory gameFactory;

    @Override
    public List<Game> getGames() {
        List<Game> gameList = new ArrayList<>();
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read("./src/main/resources/data/Game.xml");
            Element root = doc.getRootElement();
            Iterator<Element> it = root.elementIterator();
            while (it.hasNext()){
                Element e = it.next();
                Element gameID = e.element("gameID");
                Element gameName = e.element("gameName");
                gameList.add(gameFactory.createGame(Integer.valueOf(gameID.getStringValue()),
                        gameName.getStringValue()));
            }
        } catch (DocumentException e) {
            System.out.println("找不到对应文件");
            e.printStackTrace();
        }

        return gameList;
    }
}
