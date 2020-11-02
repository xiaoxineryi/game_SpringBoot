package com.kaito.game.BO.Plugin.Tagiron;

import com.kaito.game.BO.Base.BaseResponse;
import com.kaito.game.BO.Plugin.GameExtra;
import com.kaito.game.BO.Plugin.Tagiron.DTO.Function;
import com.kaito.game.BO.Plugin.Tagiron.DTO.Guess;
import com.kaito.game.BO.Plugin.Tagiron.Response.FunctionResponse;
import com.kaito.game.BO.Plugin.Tagiron.Response.GuessResponse;
import com.kaito.game.BO.Plugin.Tagiron.Response.StartResponse;

import java.util.*;

public class Tagiron implements GameExtra {

    private static Card[] cards;

    private Integer questionIndex;
    private Integer playerIndex;
    private List<Integer> questions;
    private List<Integer> question;

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
        questionIndex = 0;
        playerIndex = 0;
        questions = new ArrayList<>();
        question = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            questions.add(i);
        }
        Collections.shuffle(questions);

        for (; questionIndex < 6; questionIndex++) {
            question.add(questions.get(questionIndex));
        }

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
            players.add(new Player(null, Arrays.asList(order[i]), cardList[i]));
        }
        System.arraycopy(orders, 16, answerOrder, 0, 4);
        Arrays.sort(answerOrder);
    }

    @Override
    public List<BaseResponse> initGame(ArrayList<String> users) {
        System.out.println(Arrays.toString(answerOrder));
        usersList = users;
        for (int i = 0; i < 4; i++) {
            if (i < users.size()) {
                players.get(i).setName(users.get(i));
            } else {
                players.get(i).setName("Computer" + i);
            }
        }
        List<BaseResponse> responses = new ArrayList<>();
        for (int i = 0; i < usersList.size(); i++) {
            StartResponse response = new StartResponse(players.get(i).getCardIndex(), question, usersList.get(playerIndex));
            response.setReceiver(usersList.get(i));
            responses.add(response);
        }
        return responses;
    }

    public List<BaseResponse> play(Guess guess) {
        List<BaseResponse> responses = new ArrayList<>();
        String sender = guess.getSender();
        boolean roundFlag = sender.equals(usersList.get(playerIndex));
        if (roundFlag) {
            Boolean guessFlag = Arrays.toString(answerOrder).equals(guess.getAnswer().toString());
            System.out.println(guessFlag);
            System.out.println(Arrays.toString(guess.getAnswer().toArray()));
            playerIndex = (playerIndex + 1) % usersList.size();

            for (String s : usersList) {
                GuessResponse response = new GuessResponse(true, guessFlag, guess.getAnswer(), usersList.get(playerIndex));
                response.setReceiver(s);
                response.setSender(sender);
                responses.add(response);
            }
        } else {
            GuessResponse response = new GuessResponse(false, false, guess.getAnswer(), usersList.get(playerIndex));
            response.setReceiver(sender);
            responses.add(response);
        }
        return responses;
    }


    public List<BaseResponse> play(Function function) {
        List<BaseResponse> responses = new ArrayList<>();
        int functionNum = function.getFunction();
        String sender = function.getSender();
        boolean roundFlag = sender.equals(usersList.get(playerIndex));
        if (roundFlag) {
            List<List<Integer>> results;
            if (functionNum < 12) {
                results = location(functionNum);
            } else if (functionNum < 18) {
                results = sum(functionNum - 11);
            } else {
                results = num(functionNum - 17);
            }
            playerIndex = (playerIndex + 1) % usersList.size();
            List<String> name = new ArrayList<>();
            for (Player player : players) {
                name.add(player.getName());
            }
            question.remove((Integer) functionNum);
            question.add(questions.get(questionIndex));
            for (String s : usersList) {
                FunctionResponse response = new FunctionResponse(true, name, results, question, usersList.get(playerIndex), functionNum);
                response.setReceiver(s);
                response.setSender(sender);
                responses.add(response);
            }
            questionIndex = (questionIndex + 1) % 23;
        } else {
            FunctionResponse response = new FunctionResponse(false, null, null, null, usersList.get(playerIndex), functionNum);
            response.setReceiver(sender);
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
