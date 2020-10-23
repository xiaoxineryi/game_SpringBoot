package com.kaito.game.BO.Plugin.Desert;

import com.kaito.game.BO.Base.BaseResponse;

import com.kaito.game.BO.Plugin.Desert.DTO.Talk;
import com.kaito.game.BO.Plugin.GameExtra;

import java.util.ArrayList;

public class Desert implements GameExtra {

    public BaseResponse play(Talk talk){
        System.out.println(talk);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.getReceivers().add("kaito");
        return baseResponse;
    }



    @Override
    public BaseResponse initGame(ArrayList<String> users) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.getReceivers().add("kaito");
        return baseResponse;
    }
}
