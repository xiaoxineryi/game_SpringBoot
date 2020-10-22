package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TagironResponse extends BaseResponse {
    private String functionName;
    private List<InfoDTO> info;

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<InfoDTO> getInfo() {
        return info;
    }

    public void setInfo(List<InfoDTO> info) {
        this.info = info;
    }

}
