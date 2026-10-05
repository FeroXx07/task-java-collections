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
    private Game game;

    void main(String[] args) {
        game = new Game();
        try {
            game.init();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
