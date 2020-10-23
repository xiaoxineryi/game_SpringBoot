package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseDTO;
import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.Plugin.GameExtra;
import com.kaito.game.BO.Plugin.Tagiron.DTO.Location;
import com.kaito.game.BO.Plugin.Tagiron.DTO.Sum;

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
    public List<BaseResponse> initGame(ArrayList<String> users) {
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

    public List<BaseResponse> play(Sum sum) {
        TagironResponse response = new TagironResponse(Sum.functionName[sum.getFunction() - 1], new ArrayList<InfoDTO>());
        response.setReceivers(usersList);
        List<List<Integer>> results = sum(sum.getFunction());
        for (int i = 0; i < players.size(); i++) {
            response.getInfo().add(new InfoDTO(players.get(i).getName(), null, results.get(i)));
        }
        return response;
    }

    public List<List<Integer>> sum(int function) {
        List<List<Integer>> results = new ArrayList<>();
        for (Player player : players) {
            switch (function) {
                case 1:
                    results.add(player.sumColor(null));
                    break;
                case 2:
                    results.add(player.sumColor(Card.Blue));
                    break;
                case 3:
                    results.add(player.sumColor(Card.Red));
                    break;
                case 4:
                    results.add(player.sumLocation(0, 3));
                    break;
                case 5:
                    results.add(player.sumLocation(1, 4));
                    break;
            }
        }
        return results;
    }

    public List<BaseResponse> play(Location location) {
        int function = location.getFunction();
        TagironResponse response = new TagironResponse("数字" + function + "的位置", new ArrayList<InfoDTO>());
        response.setReceivers(usersList);
        List<List<Integer>> results = location(function);
        for (int i = 0; i < players.size(); i++) {
            response.getInfo().add(new InfoDTO(players.get(i).getName(), null, results.get(i)));
        }
        return response;
    }

    public List<List<Integer>> location(int num) {
        List<List<Integer>> results = new ArrayList<>();
        for (Player player : players) {
            results.add(player.location(num));
        }
        return results;
    }

    public static void main(String[] args) {
        Tagiron tagiron = new Tagiron();
        System.out.println(Arrays.toString(tagiron.answerOrder));
//        System.out.println(tagiron.sum());
    }
}
