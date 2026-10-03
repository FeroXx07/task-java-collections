package level_3.gestor_persones;

import java.util.Objects;

public class Persona {
    private String name;
    private String surname;
    private String dni;

    public Persona(String name, String surname, String dni) {
        validateDni(dni);

        this.name = name;
        this.surname = surname;
        this.dni = dni;
    }

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getDni() { return dni; }

    public static void validateDni(String dni) {
        // dni.isEmpty() does not take into account whitespace characters
        if (dni.isBlank()) {
            throw new IllegalArgumentException("INVALID DNI NUMBER: \"" + dni + "\". It cannot be empty");
        }
        if (dni.length() != 9) {
            throw new IllegalArgumentException("INVALID DNI NUMBER: \"" + dni + "\". It must have 9 characters. ");
        }
        // Could add also letter validation but it is out of scope of the exercise.
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
