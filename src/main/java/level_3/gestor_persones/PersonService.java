package level_3.gestor_persones;

import level_3.gestor_persones.Comparator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class PersonService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Map<Sorting, Comparator<Persona>> sortingComparatorMap;
    private PersonPersistence persistence;
    private Set<Persona> dataSet;

    public PersonService(PersonPersistence personPersistence) {
        dataSet = new HashSet<Persona>();
        persistence = personPersistence;

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
        dataSet = persistence.loadData();
    }
    public void shutdown(){
        persistence.saveData(dataSet);
        dataSet.clear();
    }


}
