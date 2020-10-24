package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.Plugin.GameExtra;
import com.kaito.game.BO.Plugin.Tagiron.DTO.Location;
import com.kaito.game.BO.Plugin.Tagiron.DTO.Num;
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
        List<BaseResponse> responses = new ArrayList<>();
        for (int i = 0; i < usersList.size(); i++) {
            TagironResponse response = new TagironResponse("游戏开始", new InfoDTO(cardList[i], null));
            response.setReceiver(usersList.get(i));
            responses.add(response);
        }
        return responses;
    }

    public List<BaseResponse> play(Sum sum) {
        List<BaseResponse> responses = new ArrayList<>();
        List<List<Integer>> results = sum(sum.getFunction());
        for (String s : usersList) {
            TagironResponse response = new TagironResponse(Sum.functionName[sum.getFunction() - 1], new InfoDTO(null, results));
            response.setReceiver(s);
            responses.add(response);
        }
        return responses;
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
                case 6:
                    results.add(player.max_min());
                    break;
            }
        }
        return results;
    }

    public List<BaseResponse> play(Location location) {
        int function = location.getFunction();
        String functionName = null;
        if (function < 10) {
            functionName = "数字" + function + "的位置";
        } else if (function == 10) {
            functionName = "相同颜色且相邻的数字板块位置";
        } else {
            functionName = "连续数字板块的位置";
        }
        List<BaseResponse> responses = new ArrayList<>();
        List<List<Integer>> results = location(function);
        for (String s : usersList) {
            TagironResponse response = new TagironResponse(functionName, new InfoDTO(null, results));
            response.setReceiver(s);
            responses.add(response);
        }
        return responses;
    }

    private List<List<Integer>> location(int num) {
        List<List<Integer>> results = new ArrayList<>();
        if (num < 10) {
            for (Player player : players) {
                results.add(player.locationNum(num));
            }
        } else {
            for (Player player : players) {
                switch (num) {
                    case 10:
                        results.add(player.location(true));
                        break;
                    case 11:
                        results.add(player.location(false));
                        break;
                }
            }
        }
        return results;
    }

    public List<BaseResponse> play(Num num) {
        int function = num.getFunction();
        List<BaseResponse> responses = new ArrayList<>();
        List<List<Integer>> results = num(function);
        for (String s : usersList) {
            TagironResponse response = new TagironResponse(Num.functionName[function - 1], new InfoDTO(null, results));
            response.setReceiver(s);
            responses.add(response);
        }
        return responses;
    }

    private List<List<Integer>> num(int num) {
        List<List<Integer>> results = new ArrayList<>();
        for (Player player : players) {
            switch (num) {
                case 1:
                    results.add(player.numColor(Card.Red, null));
                    break;
                case 2:
                    results.add(player.numColor(Card.Blue, null));
                    break;
                case 3:
                    results.add(player.numColor(null, true));
                    break;
                case 4:
                    results.add(player.numColor(null, false));
                    break;
                case 5:
                    results.add(player.numSame());
                    break;
            }
        }
        return results;
    }
}
