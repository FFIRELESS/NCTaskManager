package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.MainMenuView;

import static ua.edu.sumdu.j2se.tokarenko.tasks.utils.Exceptions.unknownModeException;

public class MainMenuController extends BaseController {
    protected static final Logger logger = Logger.getLogger(MainMenuController.class);

    private final MainMenuView mainMenuView = new MainMenuView();

    private ProgramModes addEditMenu(int var) {
        switch (var) {
            case 0:
                return ProgramModes.REMOVE;
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
            case 6:
                return ProgramModes.MAIN_MENU;
            default:
                logger.fatal("Adding or creating menu error");
                throw unknownModeException;
        }
    }

    private ProgramModes mainMenu(int var) {
        switch (var) {
            case 1:
                return ProgramModes.ADD;
            case 2:
                return ProgramModes.EDIT;
            case 3:
                return ProgramModes.PRINT_CALENDAR;
            case 4:
                return ProgramModes.PRINT_ALL;
            case 5:
                return ProgramModes.EXIT;
            default:
                logger.fatal("Main menu error");
                throw unknownModeException;
        }
    }

    @Override
    public ProgramModes process(ProgramModes mode) {
        int var;

        switch (mode) {
            case MAIN_MENU:
                mainMenuView.printMainMenu();
                var = ConsoleInputController.nextIntInRange(1, 5);
                logger.debug("Selected main menu variant: " + var);
                return mainMenu(var);

            case ADD:
                mainMenuView.printAddMenu();
                var = ConsoleInputController.nextIntInRange(1, 6);
                logger.debug("Selected adding menu variant: " + var);
                return addEditMenu(var);

            case EDIT:
                mainMenuView.printEditMenu();
                var = ConsoleInputController.nextIntInRange(0, 5);
                logger.debug("Selected editing menu variant: " + var);
                return addEditMenu(var);

            default:
                logger.fatal("Mode is incorrect: " + mode);
                throw unknownModeException;
        }
    }
}
