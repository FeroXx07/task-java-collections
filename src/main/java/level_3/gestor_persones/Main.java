package level_3.gestor_persones;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    void main(String[] args) {
        LOGGER.info("Init Program");
        try {
            DniService dniService = new DniService();
            ConsoleUI consoleUI = new ConsoleUI(dniService);
            consoleUI.Start();
        }catch (Exception e) {
            LOGGER.fatal("A fatal exception occurred. {}, {}", e.getClass().getSimpleName(), e.getMessage());
        }
        LOGGER.info("End of Program");
    }
}
