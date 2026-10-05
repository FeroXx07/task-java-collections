package level_1.exercise_3_capital_game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.ScannerUtility;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Game {
    private final static Logger LOGGER = LogManager.getLogger();
    private GameSerializer gameSerializer;

    private Map<String, String> dataMap;
    private final int gameRounds = 10;

    public Game() {
        gameSerializer = new GameSerializer();
        dataMap = new HashMap<>();
    }

    public void init()
    {
        loadData();
        int finalScore = playGame(gameRounds);
        saveScore(finalScore);
    }

    private void loadData(){
        String rawData = gameSerializer.deserializeGameData();
        Stream<String> lines = rawData.lines();
        lines.forEach(line -> {
            String[] splitLine = line.split(" ");
                if (splitLine.length == 2) {
                    String country = splitLine[0];
                    String capital = splitLine[1];
                    capital = capital.replace("_", " ");
                    dataMap.put(country, capital);
                }
        });
    }

    private void saveScore(int finalScore) {
        LOGGER.info("Your score is: {}", finalScore);
        String name = ScannerUtility.fetchStringInput("Please enter your name: ");
        String toSave = name + " " + finalScore;
        gameSerializer.serializeScore(toSave);
    }

    private int playGame(int rounds){
        if (dataMap.isEmpty()){
            throw new RuntimeException("Data of countries and capital is empty!");
        }

        int score = 0;
        for(int round = 0; round < rounds; round++){
            LOGGER.info("Round #{}: {}", round, score);
            score += playRound();
        }
        return score;
    }

    private int playRound(){
        String country = getRandomCountry();
        LOGGER.info("What is capital of {} ? (case-sensitive)", country);
        String answer = ScannerUtility.fetchStringInput("Your answer: ");
        String capital = getCapitalOf(country);

        if (capital.equals(answer)){
            LOGGER.info("Your answer is correct!");
            return 1;
        }

        LOGGER.info("Your answer is incorrect!. The capital is {}", capital);
        return 0;
    }

    private String getRandomCountry() {
        List<String> countries = new ArrayList<>(dataMap.keySet());
        return countries.get(ThreadLocalRandom.current().nextInt(countries.size()));
    }

    private String getCapitalOf(String country) {
        return dataMap.get(country);
    }

    private void displayData() {
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            LOGGER.info("Country: " + entry.getKey() + " - Capital: " + entry.getValue());
        }
    }
}
