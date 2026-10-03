package level_3.gestor_persones.Comparator;

import level_3.gestor_persones.Persona;

import java.util.Comparator;

public class SurnameCompareAsc implements Comparator<Persona> {
    @Override
    public int compare(Persona o1, Persona o2) {
        return o1.getSurname().compareToIgnoreCase(o2.getSurname());
    }
}
