package level_1.exercise_1_duplicates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();
    void main(){
        List<Month> list = new ArrayList<>(12);
        list.add(new Month("January"));
        list.add(new Month("February"));
        list.add(new Month("March"));
        list.add(new Month("April"));
        list.add(new Month("May"));
        list.add(new Month("June"));
        list.add(new Month("July"));
        list.add(new Month("September"));
        list.add(new Month("October"));
        list.add(new Month("November"));
        list.add(new Month("December"));

        list.add(7, new Month("August"));

        if (!list.get(7).getName().equals("August")) {
            throw new IllegalStateException("Unknown Collections Error");
        }

        Set<Month> set = new HashSet<>(list);
        set.add(new Month("January"));
        set.add(new Month("February"));

        for (Month month : list) {
            LOGGER.info(month);
        }

        LOGGER.info("-------------------------");

        Iterator<Month> iterator = set.iterator();
        while (iterator.hasNext()) {
            Month month = iterator.next();
            LOGGER.info(month);
        }


    }
}
