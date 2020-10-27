package com.kaito.game.BO.Plugin.Tagiron.DTO;

import com.kaito.game.BO.Base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Function extends BaseDTO {
    private String sender;
    private Integer function;

    @Override
    public BaseDTO getThis() {
        return this;
    }

    public Function() {

    }
}
