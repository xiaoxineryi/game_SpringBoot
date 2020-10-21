package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseResponse;
import lombok.Data;

import java.util.List;

public class TagironResponse extends BaseResponse {
    public TagironResponse(String sender, List<String> receivers) {
        super(sender, receivers);
    }
}
