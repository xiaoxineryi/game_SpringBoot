package com.kaito.game.BO.GameImpl;

import com.kaito.game.BO.Base.BaseDTO;
import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.GameBO;
import com.kaito.game.BO.Plugin.GameExtra;
import com.kaito.game.BO.RoomBO;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.websocket.Session;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class GameBOImpl implements GameBO {
    Hashtable<String, Session> players;
    GameExtra gameExtra;
    String className;
    @Override
    public void initGame(RoomBO roomBO, String className) throws Exception {
        String path = "com.kaito.game.BO.Plugin.";
        Class clz = Class.forName(path + className + "." + className);
        this.className = className;

        Constructor constructor = clz.getConstructor(null);
        GameExtra game = (GameExtra) constructor.newInstance();
        gameExtra = game;

        this.setPlayers(roomBO.getPlayers());
        BaseResponse o = gameExtra.initGame(new ArrayList<String>(players.keySet()));
        sendObject(o);

    }

    @Override
    public void execute(Object o) {
        try {
            HashMap<String,Object> hashtable = (HashMap)o;
            String type = String.valueOf(hashtable.get("type"));
            String DTOName = getDTOName(type);
            String path = "com.kaito.game.BO.Plugin.";
            String DTOpath = path+"DTO.";
            Class clz = Class.forName(DTOpath + className + "." +DTOName);
            Constructor constructor = clz.getConstructor(null);
            BaseDTO dto = (BaseDTO) constructor.newInstance();
            System.out.println(hashtable.get("data"));
            setDto(dto, (HashMap) hashtable.get("data"),DTOName);

            Class gameClz = Class.forName(path + className + "." + className);
            Method method = gameClz.getMethod("play",Class.forName(DTOpath+className+"."+DTOName));
            BaseResponse baseResponse= (BaseResponse) method.invoke(gameExtra,dto);
            sendObject(baseResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        BaseResponse baseResponse = gameExtra.execute(o);
    }
    private String getDTOName(String type) throws Exception {
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read("./src/main/java/com/kaito/game/BO/Plugin/"+className+"/Setting.xml");
            Element root = doc.getRootElement();
            Iterator<Element> it = root.elementIterator();
            while (it.hasNext()){
                Element e = it.next();
                Element typeID = e.element("type");
                Element className = e.element("class");
                if (type.equals(typeID.getStringValue())){
                    return className.getStringValue();
                }
            }
        } catch (DocumentException e) {
            System.out.println("找不到对应文件");
            e.printStackTrace();
        }
        throw new Exception();
    }

    private void setDto(BaseDTO dto,HashMap<String,Object> data,String dtoName){
        String DTOpath = "com.kaito.game.BO.Plugin.DTO.";
        try {
            Class clz = Class.forName(DTOpath+className+"."+dtoName);
            for (String name:data.keySet()){
                Character c = name.charAt(0);
                String subname = name.substring(1);
                Method method = clz.getMethod("set"+Character.toUpperCase(c)+subname,data.get(name).getClass());
                method.invoke(dto,data.get(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sendObject(BaseResponse baseResponse) {
        List<String> names = baseResponse.getReceivers();
        for (String name : names) {
            if (players.containsKey(name)) {
                players.get(name).getAsyncRemote().sendObject(baseResponse);
            }
        }
    }

    @Override
    public void setPlayers(Hashtable<String, Session> players) {
        this.players = players;
    }


    @Override
    public void removePlayer(String name) {
        players.remove(name);
    }
}
