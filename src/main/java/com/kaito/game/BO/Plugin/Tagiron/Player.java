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

    public List<Integer> sumColor(String color) {
        List<Integer> result = new ArrayList<>();
        int sum = 0;
        if (color == null) {
            for (Card card : cardList) {
                sum += card.getNum();
            }
        } else {
            for (Card card : cardList) {
                if (card.getColor().equals(color)) {
                    sum += card.getNum();
                }
            }
        }
        result.add(sum);
        return result;
    }

    public List<Integer> sumLocation(int i, int j) {
        List<Integer> result = new ArrayList<>();
        int sum = 0;
        for (; i < j; i++) {
            sum += cardList[i].getNum();
        }
        result.add(sum);
        return result;
    }

    public List<Integer> location(int num) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (cardList[i].getNum() == num) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
