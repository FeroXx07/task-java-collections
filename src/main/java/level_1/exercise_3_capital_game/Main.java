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

public class Main {

    private final static Logger LOGGER = LogManager.getLogger();
    private final String inputFileNameCountries = "src/main/java/level_1/exercise_3_capital_game/countries.txt";
    private final String outputFileNameClassification = "src/main/java/level_1/exercise_3_capital_game/classification.txt";
    private Map<String, String> dataMap;
    private Map<String, Integer> scoreMap;
    private final int gameRounds = 10;
    private int currentScore = 0;

    void main(String[] args) {
        try {
            loadData();
            int finalScore = playGame(gameRounds);
            saveScore(finalScore);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void loadData() {
        dataMap = new HashMap<String, String>();
        try (FileReader fr = new FileReader(inputFileNameCountries);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] splitLine = line.split(" ");
                if (splitLine.length == 2) {
                    String country = splitLine[0];
                    String capital = splitLine[1];
                    dataMap.put(country, capital);
                }
            }

        } catch (FileNotFoundException e) {
            LOGGER.error("File not found!", e);
        } catch (IOException e) {
            LOGGER.error("Error reading a file", e);
        }
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
        LOGGER.info("What is capital of {} ? (case-sensitive, for blank spaces use '_' character.)", country);
        String answer = ScannerUtility.fetchStringInput("Your answer: ");
        String capital = getCapitalOf(country);

        if (capital.equals(answer)){
            LOGGER.info("Your answer is correct!");
            return 1;
        }

        LOGGER.info("Your answer is incorrect!. The capital is {}", capital);
        return 0;
    }

    private void saveScore(int finalScore) {
        LOGGER.info("Your score is: {}", finalScore);
        String name = ScannerUtility.fetchStringInput("Please enter your name: ");
        String toSave = name + " " + finalScore;
        try (FileWriter fw = new FileWriter(outputFileNameClassification);
             BufferedWriter bw = new BufferedWriter(fw) ) {
            bw.newLine();
            bw.write(toSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
