package com.kaito.game.BO.Plugin.Desert;

import com.kaito.game.BO.Base.BaseRequest;
import com.kaito.game.BO.Base.BaseResponse;

import com.kaito.game.BO.Plugin.GameExtra;

import java.util.ArrayList;

public class Desert implements GameExtra {
    @Override
    public BaseResponse execute(BaseRequest baseRequest) {

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.getReceivers().add("kaito");
        return baseResponse;
    }
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
