package com.kaito.game.BO.Plugin.Tagiron.Response;

import com.kaito.game.BO.Base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StartResponse extends BaseResponse {
    List<Integer> card;
    List<Integer> question;
    String nextPlayer;
}
