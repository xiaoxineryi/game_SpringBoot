package com.kaito.game.BO.Plugin.Tagiron.DTO;

import com.kaito.game.BO.Base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Guess extends BaseDTO {
    private String sender;
    private ArrayList<Integer> answer;

    @Override
    public BaseDTO getThis() {
        return this;
    }

    public Guess() {
        answer = new ArrayList<>();
    }
}

