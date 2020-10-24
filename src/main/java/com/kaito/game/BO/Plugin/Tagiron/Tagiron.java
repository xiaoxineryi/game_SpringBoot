package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.Plugin.GameExtra;
import com.kaito.game.BO.Plugin.Tagiron.DTO.Function;

import java.util.*;

public class Tagiron implements GameExtra {

    private static Card[] cards;

    private Integer index;
    private List<Integer> questions;

    private List<Player> players;
    private List<String> usersList;

    private Integer[] answerOrder;

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
        index = 0;
        questions = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            questions.add(i);
        }
        Collections.shuffle(questions);

        players = new ArrayList<>();
        answerOrder = new Integer[4];
        Card[][] cardList = new Card[4][4];
        Integer[][] order = new Integer[4][4];

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
            players.add(new Player(null, order[i], cardList[i]));
        }
        System.arraycopy(orders, 16, answerOrder, 0, 4);
        Arrays.sort(answerOrder);
    }

    @Override
    public List<BaseResponse> initGame(ArrayList<String> users) {
        usersList = users;
        for (int i = 0; i < 4; i++) {
            if (i < users.size()) {
                players.get(i).setName(users.get(i));
            } else {
                players.get(i).setName("Computer" + i);
            }
        }
        List<BaseResponse> responses = new ArrayList<>();
        ArrayList<Integer> question = new ArrayList<>();
        for (; index < 6; index++) {
            question.add(questions.get(index));
        }
        for (int i = 0; i < usersList.size(); i++) {
            TagironResponse response = new TagironResponse(new InfoDTO(players.get(i).getCardIndex(), question, null));
            response.setReceiver(usersList.get(i));
            responses.add(response);
        }
        return responses;
    }

    public List<BaseResponse> play(Function function) {
        int functionNum = function.getFunction();
        List<BaseResponse> responses = new ArrayList<>();
        List<List<Integer>> results;
        if (functionNum < 12) {
            results = location(functionNum);
        } else if (functionNum < 18) {
            results = sum(functionNum - 11);
        } else {
            results = num(functionNum - 17);
        }

        for (String s : usersList) {
            TagironResponse response = new TagironResponse(new InfoDTO(null, new ArrayList<>(), results));
            response.setReceiver(s);
            response.getInfo().getQuestion().add(questions.get(index++));
            responses.add(response);
        }
        return responses;
    }

    private List<List<Integer>> sum(int function) {
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
