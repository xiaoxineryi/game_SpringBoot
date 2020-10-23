package com.kaito.game.BO.Plugin.Tagiron.DTO;

import com.kaito.game.BO.Base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sum extends BaseDTO {
    private Integer function;
    final public static String[] functionName = {"所有数字板总和",
            "所有蓝色数字板总和",
            "所有红色数字板总和",
            "左边三张数字板总和",
            "右边三张数字板总和"};

    @Override
    public BaseDTO getThis() {
        return this;
    }

    public Sum() {

    }
}
