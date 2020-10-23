package com.kaito.game.BO.Plugin.Desert;

import com.kaito.game.BO.Base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Talk extends BaseDTO{
    private Integer a;
    private Integer b;

    @Override
    public BaseDTO getThis() {
        return this;
    }

    public Talk() {

    }
}
