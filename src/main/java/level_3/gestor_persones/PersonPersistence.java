package level_3.gestor_persones;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PersonPersistence {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String dataFilePath = "src/main/resources/people.csv";
    void saveData(Set<Persona> dataSet){
        try (FileWriter fw = new FileWriter(dataFilePath);
             BufferedWriter bw = new BufferedWriter(fw) ) {
            for (Persona persona : dataSet) {
                String line = persona.getName() + "," + persona.getSurname() + "," + persona.getDni();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Set<Persona> loadData(){
        Set<Persona> dataSet = new HashSet<>();

        try(FileReader fr = new FileReader(dataFilePath);
            BufferedReader br = new BufferedReader(fr)){

            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");
                if (splitLine.length == 3) {
                    String name = splitLine[0];
                    String surname = splitLine[1];
                    String dni = splitLine[2];
                    dataSet.add(new Persona(name, surname, dni));
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found!", e);
        } catch (IOException e) {
            LOGGER.error("Error reading a file", e);
        }

        return dataSet;
    }
}
