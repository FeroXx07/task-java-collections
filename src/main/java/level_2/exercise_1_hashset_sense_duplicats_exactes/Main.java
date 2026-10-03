package level_2.exercise_1_hashset_sense_duplicats_exactes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    void main(String[] args) {
        Set<Restaurant> set = new HashSet<>();
        set.add(new Restaurant("Cangrejo Loco", 4.6f));
        set.add(new Restaurant("McDonalds", 3.7f));
        set.add(new Restaurant("Vapiano Rambla", 4.3f));
        set.add(new Restaurant("Vapiano Gran Via", 4.4f));
        set.add(new Restaurant("Cangrejo Loco", 4.6f));
        set.add(new Restaurant("Vapiano Gran Via", 4.0f));

        LOGGER.info("The following restaurants have been added: ");
        for (Restaurant r : set) {
            LOGGER.info(r);
        }
    }
}
