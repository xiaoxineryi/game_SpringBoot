package com.kaito.game.BO.Plugin.Tagiron.DTO;

import com.kaito.game.BO.Base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location extends BaseDTO {
    Integer function;

    @Override
    public BaseDTO getThis() {
        return this;
    }

    public Location() {

    }
}
