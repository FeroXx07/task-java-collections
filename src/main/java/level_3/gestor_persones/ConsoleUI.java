package level_3.gestor_persones;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.ScannerUtility;

import java.util.List;

public class ConsoleUI {
    private static final Logger LOGGER = LogManager.getLogger();
    private DniService dniService;

    public ConsoleUI(DniService dniService) {
        if (dniService == null) {
            throw new IllegalArgumentException("ReservationService cannot be null");
        }
        this.dniService = dniService;
    }

    public void Start(){
        dniService.init();
        boolean exit = false;
        do {
            try {
                switch (inputMainMenu()){
                    case 0:{
                        LOGGER.info("Thanks for using our application!");
                        dniService.shutdown();
                        exit = true;
                        break;
                    }
                    case 1:
                        handleAddPerson();
                        break;
                    case 2:
                        handleShowBy(Sorting.NAME_ASC);
                        break;
                    case 3:
                        handleShowBy(Sorting.NAME_DESC);
                        break;
                    case 4:
                        handleShowBy(Sorting.SURNAME_ASC);
                        break;
                    case 5:
                        handleShowBy(Sorting.SURNAME_DESC);
                        break;
                    case 6:
                        handleShowBy(Sorting.DNI_ASC);
                        break;
                    case 7:
                        handleShowBy(Sorting.DNI_DESC);
                        break;
                }
            }
            catch (Exception e) {
                LOGGER.warn("{}. {}", e.getClass().getSimpleName(), e.getMessage());
            }
        }while(!exit);
    }

    private void handleAddPerson() {
        String name = ScannerUtility.fetchStringInput("Enter the name of the person: ");
        String Surname = ScannerUtility.fetchStringInput("Enter the surname of the person: ");
        String dni = ScannerUtility.fetchStringInput("Enter the DNI of the person: ");
        dniService.addPerson(name, Surname, dni);
    }

    private void handleShowBy(Sorting sorting) {
        List<Persona> data = dniService.getDataSortedBy(sorting);
        LOGGER.info("There are {} people in the data", data.size());
        for (Persona persona : data) {
            LOGGER.info(persona.toString());
        }
    }

    private byte inputMainMenu() {
        byte option;
        final byte MIN = 0;
        final byte MAX = 7;

        do {
            LOGGER.info("\nMAIN MENU");
            LOGGER.info("Option 1: Add person");
            LOGGER.info("Option 2: Show people by NAME (A-Z)");
            LOGGER.info("Option 3: Show people by NAME (Z-A)");
            LOGGER.info("Option 4: Show people by SURNAME (A-Z)");
            LOGGER.info("Option 5: Show people by SURNAME (Z-A)");
            LOGGER.info("Option 6: Show people by DNI (1-9)");
            LOGGER.info("Option 7: Show people by DNI (9-1)");
            LOGGER.info("Option 0: Exit App");
            option = ScannerUtility.fetchNumber( "Select an option: ", byte.class);
            if (option < MIN || option > MAX) {
                LOGGER.info("Select a valid option!");
            }
        } while (option < MIN || option > MAX);
        return option;
    }
}
