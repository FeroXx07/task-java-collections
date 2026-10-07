package level_3.gestor_persones;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    void main(String[] args) {
        LOGGER.info("Init Program");
        try {
            PersonPersistence personPersistence = new PersonPersistence();
            PersonService personService = new PersonService(personPersistence);
            ConsoleUI consoleUI = new ConsoleUI(personService);
            consoleUI.Start();
        }catch (Exception e) {
            LOGGER.fatal("A fatal exception occurred. {}, {}", e.getClass().getSimpleName(), e.getMessage());
        }
        LOGGER.info("End of Program");
    }
}
