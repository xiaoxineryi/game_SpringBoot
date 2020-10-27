package com.kaito.game.BO.Plugin.Tagiron.Response;

import com.kaito.game.BO.Base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FunctionResponse extends BaseResponse {
    Boolean roundFlag;
    Integer question;
    List<List<Integer>> result;
    String nextPlayer;
}
