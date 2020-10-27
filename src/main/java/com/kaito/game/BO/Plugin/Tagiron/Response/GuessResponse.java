package com.kaito.game.BO.Plugin.Tagiron.Response;

import com.kaito.game.BO.Base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GuessResponse extends BaseResponse {
    Boolean roundFlag;
    Boolean guessFlag;
    List<Integer> answer;
    String nextPlayer;
}
