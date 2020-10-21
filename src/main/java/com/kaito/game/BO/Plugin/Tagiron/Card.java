package com.kaito.game.BO.Plugin.Tagiron;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    final static String Blue = "Blue";
    final static String Red = "Red";
    final static String Green = "Green";


    private String color;
    private int num;

    public int getColorNum(String color) {
        if (this.color.equals(color)) {
            return num;
        }
        return 0;
    }
}
