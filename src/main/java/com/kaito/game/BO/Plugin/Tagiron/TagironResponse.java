package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class TagironResponse extends BaseResponse {
    private InfoDTO info;
}
