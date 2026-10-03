package level_2.exercise_2_ordenacio_multiple;

import level_2.exercise_1_hashset_sense_duplicats_exactes.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    void main(String[] args) {
        Set<Restaurant> set = new HashSet<>();
        set.add(new Restaurant("Abelardo Tostadas", 1.1f));
        set.add(new Restaurant("FingerWings", 5.0f));
        set.add(new Restaurant("Cangrejo Loco", 4.9f));
        set.add(new Restaurant("Zubaldia", 4.1f));
        set.add(new Restaurant("McDonalds", 3.7f));
        set.add(new Restaurant("Vapiano Rambla", 4.3f));
        set.add(new Restaurant("Vapiano Gran Via", 4.4f));
        set.add(new Restaurant("Cangrejo Loco", 4.6f));
        set.add(new Restaurant("Vapiano Gran Via", 4.0f));

        List<Restaurant> list = new ArrayList<>(set);
        LOGGER.info("The following restaurants have been added (Ascending Alphabetical Order): ");
        Collections.sort(list);
        for (Restaurant r : list) {
            LOGGER.info(r);
        }

        LOGGER.info("The following restaurants have been added (Ascending Alphabetical Order & Rating Descending Order): ");
        Collections.sort(list, new RatingCompare());
        for (Restaurant r : list) {
            LOGGER.info(r);
        }
    }
}
