package level_3.gestor_persones.Comparator;

import level_3.gestor_persones.Persona;

import java.util.Comparator;

public class DniCompareAsc implements Comparator<Persona> {
    @Override
    public int compare(Persona o1, Persona o2) {
        return o1.getDni().compareToIgnoreCase(o2.getDni());
    }
}
