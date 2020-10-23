package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.Plugin.GameExtra;

import java.util.*;

public class Tagiron implements GameExtra {

    private static Card[] cards;

    private Card[][] cardList;
    private List<Player> players;
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
        players = new ArrayList<>();
        answerOrder = new Card[4];
        cardList = new Card[4][4];
        Integer[][] order = new Integer[4][4];
        Integer[] answer = new Integer[4];
        Integer[] orders = new Integer[20];
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            integers.add(i);
        }
        Collections.shuffle(integers);
        integers.toArray(orders);
        for (int i = 0; i < 4; i++) {
            System.arraycopy(orders, i * 4, order[i], 0, 4);
            Arrays.sort(order[i]);
            for (int j = 0; j < 4; j++) {
                cardList[i][j] = cards[order[i][j]];
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
        for (int i = 0; i < 4; i++) {
            if (i < users.size()) {
                players.add(new Player(users.get(i), cardList[i]));
            } else {
                players.add(new Player("computer " + i, cardList[i]));
            }
        }
        TagironResponse response = new TagironResponse("游戏开始", new ArrayList<InfoDTO>());
        response.setReceivers(usersList);
        for (int i = 0; i < players.size(); i++) {
            response.getInfo().add(new InfoDTO(players.get(i).getName(), cardList[i], null));
        }
        return response;
    }

//    @Override
//    public BaseResponse execute(BaseRequest baseRequest) {
//        List<List<Integer>> result = null;
//        String sender = baseRequest.getSender();
//        int function = baseRequest.getType();
//        TagironResponse response = new TagironResponse("求和", new ArrayList<InfoDTO>());
//        response.setReceivers(usersList);
//        switch (function) {
//            case 1:
//                result = sum();
//                break;
//            case 2:
//                result = location(null, 0);
//                break;
//        }
//
//        for (int i = 0; i < players.size(); i++) {
//            if (!players.get(i).getName().equals(sender)) {
//                response.getInfo().add(new InfoDTO(usersList.get(i), null, result.get(i)));
//            }
//        }
//        return response;
//    }

    public List<List<Integer>> sum() {
        List<List<Integer>> results = new ArrayList<>();
        for (Player player : players) {
            results.add(player.sum());
        }
        return results;
    }

    public List<List<Integer>> location(String color, int num) {
        List<List<Integer>> results = new ArrayList<>();
        for (Player player : players) {
            results.add(player.location(color, num));
        }
        return results;
    }

    public static void main(String[] args) {
        Tagiron tagiron = new Tagiron();
        System.out.println(Arrays.toString(tagiron.answerOrder));
        System.out.println(tagiron.sum());
    }
}
