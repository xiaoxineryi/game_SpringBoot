package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.Plugin.GameExtra;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tagiron implements GameExtra {

    @Override
    public BaseResponse initGame(ArrayList<String> users) {
        List list = new ArrayList();
        list.add(1);
        list.add("123");
        return null;
    }

    @Override
    public BaseResponse excute(Object o) {
        return null;
    }

}
