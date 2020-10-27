package com.kaito.game.BO.Plugin.Tagiron.Response;

import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.Plugin.Tagiron.Info;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FunctionResponse extends BaseResponse {
    Boolean roundFlag;
    Integer question;
    List<Info> results;
    String nextPlayer;
}
