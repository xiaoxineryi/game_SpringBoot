package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseRequest;
import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.Plugin.GameExtra;

import java.util.*;

public class Tagiron implements GameExtra {

    private static Card[] cards;

    private Integer[][] order;
    private Integer[] answer;
    private ArrayList<String> usersList;


    static {
        cards = new Card[20];
        for (int i = 0; i < 10; i++) {
            cards[i * 2] = new Card(Card.Red, i);
            cards[i * 2 + 1] = new Card(Card.Blue, i);
        }
        cards[10].setColor(Card.Green);
        cards[11].setColor(Card.Green);
    }

    public Tagiron() {
        order = new Integer[4][4];
        answer = new Integer[4];
        Integer[] cardOrder = new Integer[20];
        List<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            integers.add(i);
        }
        Collections.shuffle(integers);
        integers.toArray(cardOrder);
        for (int i = 0; i < 4; i++) {
            System.arraycopy(cardOrder, i * 4, order[i], 0, 4);
        }
        System.arraycopy(cardOrder, 16, answer, 0, 4);
    }

    @Override
    public BaseResponse initGame(ArrayList<String> users) {
        usersList = users;
        return new TagironResponse("cxz2", usersList);
    }

    @Override
    public BaseResponse execute(BaseRequest baseRequest) {
        TagironRequest tagironRequest = (TagironRequest) baseRequest;
        TagironResponse response = new TagironResponse("", usersList);
        return response;
    }

    public static void main(String[] args) {
        new Tagiron();
        System.out.println(Arrays.toString(Tagiron.cards));
    }
}
