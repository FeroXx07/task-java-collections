package level_1.exercise_3_capital_game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.ScannerUtility;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Game {
    private final static Logger LOGGER = LogManager.getLogger();
    private GameSerializer gameSerializer;
    private GameInput gameInput;
    private Map<String, String> dataMap;
    private Set<String> usedCountries;
    private final int gameRounds = 10;

    public Game() {
        gameSerializer = new GameSerializer();
        gameInput = new GameInput();

        dataMap = new HashMap<>();
        usedCountries = new HashSet<>();
    }

    public void init()
    {
        loadData();
        int finalScore = playGame(gameRounds);
        saveScore(finalScore);
    }

    private void loadData(){
        dataMap = gameSerializer.deserializeGameData();
    }

    private void saveScore(int finalScore) {
        LOGGER.info("Your score is: {}", finalScore);
        gameSerializer.serializeScore(gameInput.getPlayerName(), finalScore);
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
        String country = getRandomCountryCurated();
        return gameInput.askPlayerCapital(country, getCapitalOf(country)) ? 1 : 0;
    }

    private String getRandomCountryCurated() {
        String attempt = getRandomFromList();
        while (usedCountries.contains(attempt)) {
            attempt = getRandomFromList();
        }
        usedCountries.add(attempt);
        return attempt;
    }

    private String getRandomFromList(){
        List<String> countries = new ArrayList<>(dataMap.keySet());
        return countries.get(ThreadLocalRandom.current().nextInt(countries.size()));
    }

    private String getCapitalOf(String country) {
        return dataMap.get(country);
    }

//    private void displayData() {
//        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
//            LOGGER.info("Country: " + entry.getKey() + " - Capital: " + entry.getValue());
//        }
//    }
}
