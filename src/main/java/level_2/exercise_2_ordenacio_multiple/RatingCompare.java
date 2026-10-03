package level_2.exercise_2_ordenacio_multiple;

import level_2.exercise_1_hashset_sense_duplicats_exactes.Restaurant;

import java.util.Comparator;

public class RatingCompare implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant o1, Restaurant o2) {
        // First: name ascending
        int nameComparison = o1.getName().compareTo(o2.getName());

        if (nameComparison != 0) {
            return nameComparison;
        }

        // If names are equal: rating descending
        return Float.compare(o2.getRating(), o1.getRating());
    }
}
