package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.MainMenuView;

public class MainMenuController extends BaseController {
    protected static final Logger logger =
            Logger.getLogger(MainMenuController.class);

    /**
     * Cass to display information in the console
     */
    private final MainMenuView menuView = new MainMenuView();

    /**
     * Method that controls the creation and display all menus of the program
     * @param programMode constant indicating which menu to display
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramModes process(ProgramModes programMode) {
        int options;
        if (programMode.equals(ProgramModes.MAIN_MENU)) {
            menuView.printMainMenu();
            options = ConsoleInputController.nextIntInRange(1, 5);

            logger.debug("Selected menu item " + options);

            switch (options) {
                case 1:
                    return ProgramModes.ADD;
                case 2:
                    return ProgramModes.CHANGE;
                case 3:
                    return ProgramModes.PRINT_CALENDAR;
                case 4:
                    return ProgramModes.PRINT_ALL;
                case 5:
                    return ProgramModes.EXIT;
            }

        } else if (programMode.equals(ProgramModes.ADD)) {
            menuView.printAddMenu();
            options = ConsoleInputController.nextIntInRange(1, 5);

            logger.debug("Selected menu item " + options);

            switch (options) {
                case 1:
                    return ProgramModes.SET_TITLE;
                case 2:
                    return ProgramModes.SET_ACTIVE;
                case 3:
                    return ProgramModes.SET_REPEATING_TIME;
                case 4:
                    return ProgramModes.SET_NON_REPEATING_TIME;
                case 5:
                    return ProgramModes.SAVE;
            }

        } else if (programMode.equals(ProgramModes.CHANGE)) {
            menuView.printChangeMenu();
            options = ConsoleInputController.nextIntInRange(1, 5);

            logger.debug("Selected menu item " + options);

            switch (options) {
                case 1:
                    return ProgramModes.SET_TITLE;
                case 2:
                    return ProgramModes.SET_ACTIVE;
                case 3:
                    return ProgramModes.SET_REPEATING_TIME;
                case 4:
                    return ProgramModes.SET_NON_REPEATING_TIME;
                case 5:
                    return ProgramModes.SAVE;
            }

        }

        throw new IllegalStateException("Program status is incorrect!");
    }
}
