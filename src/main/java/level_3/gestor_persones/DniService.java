package level_3.gestor_persones;

import level_3.gestor_persones.Comparator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DniService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String dataFilePath = "src/main/resources/people.csv";
    private final Map<Sorting, Comparator<Persona>> sortingComparatorMap;
    private Set<Persona> dataSet;

    public DniService() {
        dataSet = new HashSet<Persona>();

        sortingComparatorMap = new HashMap<Sorting, Comparator<Persona>>();
        sortingComparatorMap.put(Sorting.NAME_ASC, new NameCompareAsc());
        sortingComparatorMap.put(Sorting.NAME_DESC, new NameCompareDesc());
        sortingComparatorMap.put(Sorting.SURNAME_ASC, new SurnameCompareAsc());
        sortingComparatorMap.put(Sorting.SURNAME_DESC, new SurnameCompareDesc());
        sortingComparatorMap.put(Sorting.DNI_ASC, new DniCompareAsc());
        sortingComparatorMap.put(Sorting.DNI_DESC, new DniCompareDesc());
    }

    public void addPerson(String name, String surname, String dni) {
        dataSet.add(new Persona(name, surname, dni));
    }
    public List<Persona> getDataSortedBy(Sorting sorting) {
        Comparator<Persona> comparator = sortingComparatorMap.get(sorting);
        return dataSet
                .stream()
                .sorted(comparator)
                .toList();
    }

    public void init(){
        loadData();
    }
    public void shutdown(){
        saveData();
        dataSet.clear();
    }

    private void saveData(){

    }

    private void loadData(){
        try(FileReader fr = new FileReader(dataFilePath);
            BufferedReader br = new BufferedReader(fr)){

            // Skip header or column lines of the csv
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");
                if (splitLine.length == 3) {
                    String name = splitLine[0];
                    String surname = splitLine[1];
                    String dni = splitLine[2];
                    addPerson(name, surname, dni);
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found!", e);
        } catch (IOException e) {
            LOGGER.error("Error reading a file", e);
        }
    }
}
