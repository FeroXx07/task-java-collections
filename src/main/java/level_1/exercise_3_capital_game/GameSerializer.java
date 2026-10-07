package level_1.exercise_3_capital_game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.ScannerUtility;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class GameSerializer {
    private final static Logger LOGGER = LogManager.getLogger();
    private final String inputFileNameCountries = "src/main/resources/countries.txt";
    private final String outputFileNameClassification = "src/main/resources/classification.txt";

    public void serializeScore(String playerName, int finalScore) {
        try (FileWriter fw = new FileWriter(outputFileNameClassification, true);
             BufferedWriter bw = new BufferedWriter(fw) ) {
            bw.newLine();
            String toSave = playerName + " " + finalScore;
            bw.write(toSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> deserializeGameData() {
        Map<String, String> data = new HashMap<>();

        try (FileReader fr = new FileReader(inputFileNameCountries);
             BufferedReader br = new BufferedReader(fr)) {
            Stream<String> lines = br.lines();
            lines.forEach(line -> {
                String[] splitLine = line.split(" ");
                if (splitLine.length == 2) {
                    String country = splitLine[0];
                    String capital = splitLine[1];
                    capital = capital.replace("_", " ");
                    data.put(country, capital);
                }
            });
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found!", e);
        } catch (IOException e) {
            LOGGER.error("Error reading a file", e);
        }

        return data;
    }

//    private String readAllLines(BufferedReader reader) throws IOException {
//        StringBuilder content = new StringBuilder();
//        String line;
//
//        while ((line = reader.readLine()) != null) {
//            content.append(line);
//            content.append(System.lineSeparator());
//        }
//
//        return content.toString();
//    }
}
