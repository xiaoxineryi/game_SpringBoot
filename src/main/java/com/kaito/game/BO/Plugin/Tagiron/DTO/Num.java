package com.kaito.game.BO.Plugin.Tagiron.DTO;


import com.kaito.game.BO.Base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Num extends BaseDTO {
    Integer function;

    final public static String[] functionName = {"红色数字板数量",
            "蓝色数字板数量",
            "偶数数字板数量",
            "奇数数字板数量",
            "相同数字板数量"};

    @Override
    public BaseDTO getThis() {
        return this;
    }

    public Num() {
    }
}


