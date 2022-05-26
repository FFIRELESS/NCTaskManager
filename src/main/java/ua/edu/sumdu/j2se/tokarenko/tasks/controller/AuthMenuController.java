package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.MainMenuView;

import static ua.edu.sumdu.j2se.tokarenko.tasks.utils.Exceptions.unknownModeException;

public class AuthMenuController extends BaseController {
    protected static final Logger logger = Logger.getLogger(AuthMenuController.class);

    private final MainMenuView mainMenuView = new MainMenuView();

    /**
     * Метод контролю меню входу в програму.
     *
     * @return наступний(обраний) режим програми.
     */
    @Override
    public ProgramModes authMenuProcess() {
        mainMenuView.printAuthorizeMenu();
        int var = ConsoleInputController.nextIntInRange(1, 3);
        logger.debug("Selected auth menu variant: " + var);

        switch (var) {
            case 1:
                return ProgramModes.AUTHORIZE;
            case 2:
                return ProgramModes.REGISTER;
            case 3:
                return ProgramModes.EXIT;
            default:
                logger.fatal("Auth menu error");
                throw unknownModeException;
        }
    }
}
