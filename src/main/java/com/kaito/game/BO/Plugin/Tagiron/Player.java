package com.kaito.game.BO.Plugin.Tagiron;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Player {
    private String name;
    private Card[] cardList;

    public List<Integer> sum() {
        List<Integer> result = new ArrayList<>();
        int sum = 0;
        for (Card card : cardList) {
            sum += card.getNum();
        }
        result.add(sum);
        return result;
    }

    public List<Integer> location(String color, int num) {
        List<Integer> result = new ArrayList<>();
        if (color == null) {
            for (int i = 0; i < 4; i++) {
                if (cardList[i].getNum() == num) {
                    result.add(i + 1);
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                if (cardList[i].getNum() == num && cardList[i].getColor().equals(color)) {
                    result.add(i + 1);
                }
            }
        }
        return result;
    }
}
