package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseRequest;
import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.Plugin.GameExtra;

import java.util.*;

public class Tagiron implements GameExtra {

    private static Card[] cards;

    private Card[][] cardOrder;
    private Card[] answerOrder;
    private List<String> usersList;


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
        answerOrder = new Card[4];
        cardOrder = new Card[4][4];
        Integer[][] order = new Integer[4][4];
        Integer[] answer = new Integer[4];
        Integer[] orders = new Integer[20];
        List<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            integers.add(i);
        }
        Collections.shuffle(integers);
        integers.toArray(orders);
        for (int i = 0; i < 4; i++) {
            System.arraycopy(orders, i * 4, order[i], 0, 4);
            Arrays.sort(order[i]);
            for (int j = 0; j < 4; j++) {
                cardOrder[i][j] = cards[order[i][j]];
            }
        }
        System.arraycopy(orders, 16, answer, 0, 4);
        Arrays.sort(answer);
        for (int i = 0; i < 4; i++) {
            answerOrder[i] = cards[answer[i]];
        }
    }

    @Override
    public BaseResponse initGame(ArrayList<String> users) {
        usersList = users;
        TagironResponse response = new TagironResponse("游戏开始", new ArrayList<InfoDTO>());
        response.setReceivers(usersList);
        for (int i = 0; i < usersList.size(); i++) {
            response.getInfo().add(new InfoDTO(usersList.get(i), cardOrder[i], null));
        }
        return response;
    }

    @Override
    public BaseResponse execute(BaseRequest baseRequest) {
        TagironRequest tagironRequest = (TagironRequest) baseRequest;
        List<Integer[]> result = sum();

        String sender = tagironRequest.getSender();
        TagironResponse response = new TagironResponse("求和", new ArrayList<InfoDTO>());
        response.setReceivers(usersList);
        for (int i = 0; i < usersList.size(); i++) {
            if (!usersList.get(i).equals(sender)) {
                response.getInfo().add(new InfoDTO(usersList.get(i), null, result.get(i)));
            }
        }
        return response;
    }

    public List<Integer[]> sum() {
        List<Integer[]> result = new ArrayList<Integer[]>();
        Integer[][] sum = new Integer[4][1];
        for (int i = 0; i < 4; i++) {
            sum[i][0] = 0;
            for (int j = 0; j < 4; j++) {
                sum[i][0] += cardOrder[i][j].getNum();
            }
            result.add(sum[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Tagiron tagiron = new Tagiron();
        System.out.println(Arrays.toString(tagiron.answerOrder));
        System.out.println(Arrays.toString(tagiron.sum().get(0)));
    }
}
