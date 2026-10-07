package level_1.exercise_3_capital_game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.ScannerUtility;

public class GameInput {
    private final static Logger LOGGER = LogManager.getLogger();
    private final String playerName = askForPlayerName();
    public String getPlayerName() {return playerName;}

    public boolean askPlayerCapital(String country, String capital){
        LOGGER.info("What is capital of {} ? (case-sensitive)", country);
        String answer = ScannerUtility.fetchStringInput("Your answer: ");

        if (capital.equals(answer)){
            LOGGER.info("Your answer is correct!");
            return true;
        }

        LOGGER.info("Your answer is incorrect!. The capital is {}", capital);
        return false;
    }
    private final String askForPlayerName() {
        return ScannerUtility.fetchStringInput("Please enter your name: ");
    }
}
