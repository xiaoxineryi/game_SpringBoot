package com.kaito.game.BO.Plugin.Tagiron;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@AllArgsConstructor
public class Player {
    private String name;
    private Integer[] cardIndex;
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

    public List<Integer> max_min() {
        List<Integer> result = new ArrayList<>();
        result.add(cardList[3].getNum() - cardList[0].getNum());
        return result;
    }

    public List<Integer> locationNum(int num) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (cardList[i].getNum() == num) {
                result.add(i + 1);
            }
        }
        if (result.isEmpty()) {
            result.add(0);
        }
        return result;
    }

    public List<Integer> location(boolean flag) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (flag) {
                if (cardList[i].getColor().equals(cardList[i + 1].getColor())) {
                    result.add(i + 1);
                    result.add(i + 2);
                }
            } else {
                if (cardList[i].getNum() + 1 == cardList[i + 1].getNum()) {
                    result.add(i + 1);
                    result.add(i + 2);
                }
            }
        }
        result = new ArrayList<Integer>(new HashSet<Integer>(result));
        return result;
    }

    public List<Integer> numColor(String color, Boolean parity) {
        List<Integer> result = new ArrayList<>();
        int num = 0;
        if (color == null) {
            if (parity) {
                for (Card card : cardList) {
                    if (card.getNum() % 2 == 0)
                        num++;
                }
            } else {
                for (Card card : cardList) {
                    if (card.getNum() % 2 != 0)
                        num++;
                }
            }
        } else {
            for (Card card : cardList) {
                if (card.getColor().equals(color))
                    num++;
            }
        }
        result.add(num);
        return result;
    }

    public List<Integer> numSame() {
        List<Integer> result = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (cardList[i].getNum() == cardList[j].getNum()) {
                    num++;
                    break;
                }
            }
        }
        result.add(num);
        return result;
    }
}
