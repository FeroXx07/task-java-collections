package level_1.exercise_2_list_iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    final int size = 10;
    void main(String[] args) {

        List<Integer> list = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }

        List<Integer> reverseList = new ArrayList<>(list.reversed());

        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            reverseList.add(listIterator.next());
        }

        LOGGER.info(reverseList);
    }
}
