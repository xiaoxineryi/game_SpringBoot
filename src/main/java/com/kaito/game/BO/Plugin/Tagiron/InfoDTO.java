package com.kaito.game.BO.Plugin.Tagiron;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class InfoDTO {
    public InfoDTO() {

    }

    Integer[] cards;
    List<Integer> question;
    List<List<Integer>> result;
}
