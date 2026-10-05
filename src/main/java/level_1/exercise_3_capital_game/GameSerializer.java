package level_1.exercise_3_capital_game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.ScannerUtility;

import java.io.*;
import java.util.HashMap;

public class GameSerializer {
    private final static Logger LOGGER = LogManager.getLogger();

    private final String inputFileNameCountries = "src/main/resources/countries.txt";
    private final String outputFileNameClassification = "src/main/resources/classification.txt";

    public void serializeScore(String args) {
        try (FileWriter fw = new FileWriter(outputFileNameClassification, true);
             BufferedWriter bw = new BufferedWriter(fw) ) {
            bw.newLine();
            bw.write(args);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String deserializeGameData() {
        try (FileReader fr = new FileReader(inputFileNameCountries);
             BufferedReader br = new BufferedReader(fr)) {
            return readAllLines(br);
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found!", e);
        } catch (IOException e) {
            LOGGER.error("Error reading a file", e);
        }
        return null;
    }

    private String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }
}
