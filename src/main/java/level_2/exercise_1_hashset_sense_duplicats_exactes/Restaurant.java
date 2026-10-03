package level_2.exercise_1_hashset_sense_duplicats_exactes;

import java.util.Objects;

public class Restaurant {
    private final String name;
    private final float rating;

    public Restaurant(String name, float rating) {
        this.name = name;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Float.compare(rating, that.rating) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
